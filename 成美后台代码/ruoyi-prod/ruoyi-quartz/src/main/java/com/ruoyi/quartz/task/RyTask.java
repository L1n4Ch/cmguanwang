package com.ruoyi.quartz.task;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUtils;
import com.tinify.Source;
import com.tinify.Tinify;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {
    private static final Logger logger = LoggerFactory.getLogger(RyTask.class);

    @Autowired
    private ICmFileInfoService iCmFileInfoService;

    @Value("${tinypng.key}")
    private String tinypngKey;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }

    private String[] imgType = new String[]{"png", "jpg", "jpeg", "PNG", "JPG", "JPEG"};

    /**
     * 压缩图片
     *
     * @param execFlag 是否压缩处理
     * @param size     多大的图片进行处理，单位M
     */
    public void zipImg(Boolean execFlag, Integer size) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            String profile = RuoYiConfig.getProfile();
            File f = new File(profile);
            List<File> bigFile = new ArrayList<File>();
            getBigFile(f, bigFile, size);
            bigFile.stream().forEach(x -> {
                logger.info(x.getAbsolutePath());
            });

            String[] keys = tinypngKey.split(",");
            int idx = 0;
            // 超过1M 进行处理，将文件重命名
            if (execFlag) {
                List<File> execList = new ArrayList<>();
                Iterator<File> iterator = bigFile.iterator();
                while (iterator.hasNext()) {
                    execList.add(iterator.next());
                    // 只能免费处理500张
                    if (execList.size() == 400) {
                        if(idx == keys.length){
                            logger.info("没有可以压缩的key 了。");
                            return;
                        }
                        // 复制数组
                        List<File> workList = new ArrayList<>(400);
                        execList.stream().forEach(x->{
                            workList.add(x);
                        });
                        executorService.execute(new Worker(workList, keys[idx]));
                        execList.clear(); // 清空列表
                        idx ++;
                    }
                }
                if(execList.size() > 0){
                    if(idx == keys.length){
                        logger.info("没有可以压缩的key 了。");
                        return;
                    }
                    executorService.execute(new Worker(execList, keys[idx]));
                }

            }

        } catch (Exception e) {
            logger.error("压缩图片处理失败.....",e);
        }finally {
            executorService.shutdown();
        }
    }

    private void getBigFile(File f, List<File> bigFile, Integer bigSize) {

        if (f.isDirectory()) {
            // 如果f是目录
            File[] files = f.listFiles();
            for (File file : files) {
                getBigFile(file, bigFile, bigSize);
            }
        } else {
            // 是文件，判断是否是图片
            String fileName = f.getName();
            if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (Arrays.asList(imgType).contains(ext)) {
                    //图片进行处理
                    double size = f.length() / 1024.0 / 1024.0;
                    if (size > bigSize) {
                        bigFile.add(f);
                    }
                }
            }
        }
    }

    static class Worker implements Runnable{
        private List<File> workFileList;
        private String tinypngKey;

        public Worker(List<File> workFileList, String tinypngKey) {
            this.workFileList = workFileList;
            this.tinypngKey = tinypngKey;
        }
        @Autowired
        public void run() {
            for (File f : workFileList) {
                try {
                    String fileName = f.getName();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    String absolutePath = f.getAbsolutePath();
                    String path = absolutePath.substring(0, absolutePath.lastIndexOf("."));
                    File tempFile = new File(path + "-temp." + ext);
                    logger.info(absolutePath + "--->" + tempFile.getAbsolutePath());
                    //Thumbnails.of(f).scale(1f).outputQuality(scala).toFile(tempFile);
                    Tinify.setKey(tinypngKey);
                    Source source = Tinify.fromFile(absolutePath);
                    source.toFile(tempFile.getAbsolutePath());
                    // 删除之前的文件
                    f.delete();
                    tempFile.renameTo(new File(absolutePath));
                } catch (IOException e) {
                    logger.error("图片处理失败.....", e);
                } catch (Exception e) {
                    logger.error("图片处理失败.....", e);
                }
            }
        }
    }

    /**
     * 定时清理不需要的图片
     */
    public void delImgFile() {
        String profile = RuoYiConfig.getProfile();
        //取出所有没有引用的图片
        List<CmFileInfo> cmFileInfos =
                iCmFileInfoService.selectCmFileInfoNotUse();

        cmFileInfos.stream().forEach(x -> {
            iCmFileInfoService.deleteCmFileInfoById(x.getfId());
        });
        // 获取所有的文件
        List<CmFileInfo> fileList = iCmFileInfoService.selectCmFileInfoList(null);
        fileList.stream().forEach(x -> {
            //判断文件是否存在
            String fileUrl = x.getFileUrl();

            String[] arr = fileUrl.split("/Public/");
            if (arr.length > 1) {
                File file = new File(profile + arr[1]);
                // 路径为文件且不为空则进行删除
                if (file.isFile() && file.exists()) {

                } else {
                    // 写一个图片
                    createImg(file);
                }
            }


        });
    }

    private static void createImg(File file) {

        String absolutePath = file.getAbsolutePath();
        String filePath = absolutePath.
                substring(0, absolutePath.lastIndexOf(File.separator));


        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs(); // 创建目录
        }
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            int width = 100;
            int height = 100;
            String s = "自动生产的图片";

            Font font = new Font("Serif", Font.BOLD, 10);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = (Graphics2D) bi.getGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0, 0, width, height);
            g2.setPaint(Color.RED);

            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(s, context);
            double x = (width - bounds.getWidth()) / 2;
            double y = (height - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double baseY = y + ascent;

            g2.drawString(s, (int) x, (int) baseY);

            try {
                ImageIO.write(bi, ext, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
