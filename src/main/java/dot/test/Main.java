package dot.test;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.Graphics2D ;   
import java.awt.image.* ;   

public class Main {

    private static String inputImgPath = "src/main/resources/td3.jpg";
    private static String outPutImagePath = "src/main/resources/outputImage2.jpg";
   
    private static String tessdataPath = "src/main/resources/tessdata";
   
    
    public static void processImg( BufferedImage inputImage, float scaleFactor, float offset )  
        throws IOException, TesseractException  
    {         
     
    // We will create an image buffer  
    // for storing the image later on  
    // and inputImage is an image buffer  
    // of input image  
    BufferedImage outputImage = new BufferedImage( 1050, 1024, inputImage.getType( ) ) ;  
    // Now, for drawing the new image  
    // we will create a 2D platform  
    // on the buffer image  
    Graphics2D grp = outputImage.createGraphics( ) ;  
    // drawing a new zoomed image starting  
    // from 0 0 of size 1050 x 1024  
    // and null is the ImageObserver class object  
    grp.drawImage( inputImage, 0, 0, 1050, 1024, null ) ;  
    grp.dispose( ) ;          
    // for the gray scaling of images  
    // we'll use RescaleOp object  
    RescaleOp rescaleOutput = new RescaleOp( scaleFactor, offset, null ) ;    
    // Here, we are going to perform  
    // scaling of the image and then  
    // writing on a .jpg file  
    BufferedImage finalOutputimage = rescaleOutput.filter( outputImage, null ) ;  
    ImageIO.write( finalOutputimage, " jpg ",  
                new File( outPutImagePath ) ) ;  
    // Creating an instance of Tesseract class  
    // that will be used to perform OCR 
    
     
    Tesseract tesseractInstance = new Tesseract( ) ;  
    tesseractInstance.setDatapath( tessdataPath) ;  
    tesseractInstance.setLanguage("deu");
    tesseractInstance.setPageSegMode(4);
    // finally performing OCR on the image  
    // and then storing the result in 'str' string  
    String str = tesseractInstance.doOCR( inputImage ) ;  
    System.out.println( str ) ;  
    }  



    public static void main(String[] args)  throws Exception  {

        File image = new File(inputImgPath);

        BufferedImage inputImage = ImageIO.read( image) ;  
        // here, we're getting the RGB content of the complete image file  
        double d = inputImage.getRGB(inputImage.getTileWidth( ) / 2,     
                                          inputImage.getTileHeight() / 2 ) ;  
        // now, we'll compare the values and  
        // set up new scaling values  
        // which will be use by RescaleOp later on  
        if ( d >= -1.4211511E7 && d < -7254228 ) {  
            processImg( inputImage, 3f, -10f ) ;  
        }  
        else if ( d >= -7254228 && d < -2171170 ) {  
            processImg( inputImage, 1.455f, -47f ) ;  
        }  
        else if ( d >= -2171170 && d < -1907998 ) {  
            processImg( inputImage, 1.35f, -10f ) ;  
        }  
        else if ( d >= -1907998 && d < -257 ) {  
            processImg( inputImage, 1.19f, 0.5f ) ;  
        }  
        else if ( d >= -257 && d < -1 ) {  
            processImg( inputImage, 1f, 0.5f ) ;  
        }  
        else if ( d >= -1 && d < 2 ) {  
            processImg( inputImage, 1f, 0.35f ) ;  
        }  

        /*
Tesseract tesseract = new Tesseract();
tesseract.setDatapath(tessdataPath);
tesseract.setLanguage("deu");
tesseract.setPageSegMode(1);
tesseract.setOcrEngineMode(1);
String result;
try {
    result = tesseract.doOCR(image);
    System.out.println(result);
} catch (TesseractException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
 */
    }
}