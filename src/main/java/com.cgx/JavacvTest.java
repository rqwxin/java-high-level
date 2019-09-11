package com.cgx;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-09-11 21:23
 **/
public class JavacvTest {

    //视频文件路径
    private static String videoPath = "I:/study";

    //视频帧图片存储路径
    public static String videoFramesPath = "I:/study";

    /**
     * TODO 将视频文件帧处理并以“jpg”格式进行存储。
     * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
     *
     * @param videoFileName
     * @param mod
     */
    public static void grabberVideoFramer(String videoFileName, int mod) {
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        /*
            获取视频文件
         */
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);

        try {
            fFmpegFrameGrabber.start();
            /*
            .getFrameRate()方法：获取视频文件信息,总帧数
             */
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
//            System.out.println(fFmpegFrameGrabber.grabKeyFrame());
            System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);

            BufferedImage bImage = null;
            System.out.println("开始运行视频提取帧，耗时较长");
            if (mod>ftp){
                mod = ftp;
            }
            for (int i = 0; i < ftp; i++) {
                if (i==mod){
                    //获取帧
                    frame = fFmpegFrameGrabber.grabImage();
                    break;
                }
            }
            if (frame != null) {
                //文件绝对路径+名字
                String fileName = videoFramesPath + "/img_.jpg";
                //文件储存对象
                File outPut = new File(fileName);
                ImageIO.write(FrameToBufferedImage(frame), "jpg", outPut);
            }
            System.out.println("============运行结束============");
            fFmpegFrameGrabber.stop();
        } catch (IOException E) {

        }
    }


    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }
    /*
        测试.....
     */
    public static void main(String[] args) {
        String videoFileName = "01.mp4";
        grabberVideoFramer(videoFileName,1990);
    }

}
