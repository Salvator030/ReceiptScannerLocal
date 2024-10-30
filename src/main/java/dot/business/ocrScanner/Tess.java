package dot.business.ocrScanner;

import java.io.File;
import dot.business.handler.FileHandler;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Tess {

  
    private static String outPutImagePath = "src/main/resources/test.JPG";
    private static final String tessdataPath = "src/main/resources/tessdata";
    private  Tesseract tesseract;

   public Tess(){
        tesseract = new Tesseract();
        setTessaract();
    }

    private  void setTessaract() {
        tesseract.setDatapath(tessdataPath);
        tesseract.setLanguage("deu_rece");
        tesseract.setPageSegMode(4);
        tesseract.setOcrEngineMode(1);

    }

 
    public String tess4jNormal(File image) {

        String result = null;
        try {
            System.out.println("Normal");
            result = tesseract.doOCR(image);
            System.out.println(result);
            FileHandler.writeToFile("Normal", result);
        } catch (TesseractException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }



    

    // public static void processImg(BufferedImage inputImage, float scaleFactor, float offset,
    //         Tesseract tesseractInstance)
    //         throws IOException, TesseractException {

    //     // We will create an image buffer
    //     // for storing the image later on
    //     // and inputImage is an image buffer
    //     // of input image
    //     BufferedImage outputImage = new BufferedImage(1050, 1024, inputImage.getType());
    //     // Now, for drawing the new image
    //     // we will create a 2D platform
    //     // on the buffer image
    //     Graphics2D grp = outputImage.createGraphics();
    //     // drawing a new zoomed image starting
    //     // from 0 0 of size 1050 x 1024
    //     // and null is the ImageObserver class object
    //     grp.drawImage(inputImage, 0, 0, 1050, 1024, null);
    //     grp.dispose();
    //     // for the gray scaling of images
    //     // we'll use RescaleOp object
    //     RescaleOp rescaleOutput = new RescaleOp(scaleFactor, offset, null);
    //     // Here, we are going to perform
    //     // scaling of the image and then
    //     // writing on a .jpg file
    //     BufferedImage finalOutputimage = rescaleOutput.filter(outputImage, null);
    //     File newFile = new File(outPutImagePath);
    //     File newFile2 = new File("src/main/resources/test2.JPG");

    //     ImageDeskew imageDeskew1 = new ImageDeskew(inputImage);
    //     double a = imageDeskew1.getSkewAngle();
    //     ImageDeskew imageDeskew2 = new ImageDeskew(outputImage);

    //     double b = imageDeskew2.getSkewAngle();

    //     inputImage = ImageHelper.rotateImage(inputImage, -imageDeskew1.getSkewAngle());
    //     finalOutputimage = ImageHelper.rotateImage(outputImage, -imageDeskew2.getSkewAngle());

    //     System.out.println(ImageIO.write(inputImage, "JPG", newFile2));
    //     System.out.println(ImageIO.write(outputImage, "JPG", newFile));

    //     newFile.createNewFile();
    //     newFile2.createNewFile();

    //     System.out.println(Files.exists(Paths.get(outPutImagePath)));

    //     // finally performing OCR on the image
    //     // and then storing the result in 'str' string
    //     System.out.println("Fetched");
    //     String str = tesseractInstance.doOCR(outputImage);
    //     System.out.println(str);
    //     wrideToFile("Fetched", str);
    // }

    // public static void tess4jWithScaling(File image) {
    //     try {
    //         BufferedImage inputImage = ImageIO.read(image);
    //         // here, we're getting the RGB content of the complete image file
    //         double d = inputImage.getRGB(inputImage.getTileWidth() / 2,
    //                 inputImage.getTileHeight() / 2);
    //         // now, we'll compare the values and
    //         // set up new scaling values
    //         // which will be use by RescaleOp later on
    //         if (d >= -1.4211511E7 && d < -7254228) {
    //             processImg(inputImage, 3f, -10f, tesseract);
    //         } else if (d >= -7254228 && d < -2171170) {
    //             processImg(inputImage, 1.455f, -47f, tesseract);
    //         } else if (d >= -2171170 && d < -1907998) {
    //             processImg(inputImage, 1.35f, -10f, tesseract);
    //         } else if (d >= -1907998 && d < -257) {
    //             processImg(inputImage, 1.19f, 0.5f, tesseract);
    //         } else if (d >= -257 && d < -1) {
    //             processImg(inputImage, 1f, 0.5f, tesseract);
    //         } else if (d >= -1 && d < 2) {
    //             processImg(inputImage, 1f, 0.35f, tesseract);
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }


    // public static void run() throws Exception {
    //     File image = new File(inputImgPath);
    //     setTessaract();
    //     String result = tess4jNormal(image, tesseract);
    //     System.out.println(ItemScanner.getStoreName(result));
    //     System.out.println(ItemScanner.getTotalSumm(result));
    //     System.out.println(ItemScanner.getDate(result));



        // tess4jWithScaling(image);

    }

