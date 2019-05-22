
package User_GUI;

import Stegnography.EmbProcess;
import enc.DBS;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;


/**
 *
 * @author pc
 */
public class VideoPlayer extends Application {
    private static String path="";
    
    
    public static void main(String[]args)
    {
        launch(args);
    }
    
    
    @Override
    public void start(final Stage stage)throws Exception
    {
        Group root = new Group();
        
        FileDialog filedialog = new FileDialog(new JFrame(), "Select Video File");
        filedialog.setFile("*.mp4;*.avi;*.flv;*");
        System.out.println("before get file");
        String s = new String();
            filedialog.setVisible(true);
            filedialog.getFile();
            System.out.println("after get file");
            path = "file:/"+filedialog.getDirectory()+ filedialog.getFile()+"";
            path = path.replace("\\", "/");
            System.out.println(path);
            URI u;
            //Media media = new Media("file:/D:/123.mp4");
            Media media = new Media(path);
            
           // de-embed process
            
            try{
                System.out.println("De-Embedding");
                System.out.println("Before string replace: "+path);
                path = path.replace("/", "\\");
                System.out.println("After string replace: "+path);
                path = path.replaceFirst("file:", "");
                System.out.println("After file remove: "+path);
            String s1 = path;
            System.out.println(path);
            ep = new EmbProcess();
            String genfile = ep.demb(s1);
            if (genfile != null) {
                System.out.println( " De-Embed Process Completed");
                } else {
                    System.out.println( " De-Embed Process Failed");
                }
            }catch(Exception e){System.out.println(e);}
            
            //wait(100);
            // decryption process
            
            try{
                //path = path.replace("/", "\\");
            String s2 = filedialog.getDirectory()+ "enc";

            boolean flag = new DBS().DBST(7, s2);
            if (flag) {
            System.out.println("\nFile Decryption Sucessful");
            } else {
            System.out.println("\nFile Decryption failed");
            }
            }catch(Exception e){System.out.println(e);}
            
            // get time from decrypted file
            
          
            
        try
        {
            File file = null;
            String data = null;
            WordExtractor extractor = null;
            file = new File("D:/docfile.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            
            HWPFDocument document = new HWPFDocument(fis);
            extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();
            for (int i = 0; i < fileData.length; i++)
            {
                if (fileData[i] != null)
                    System.out.println(fileData[i]);
                data = fileData[i];
            }
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            
            String stringdate = new SimpleDateFormat("hh:mm").format(new Date());
            Date date = null;
            Date date2 = null;
             
            //String str = new Date().toString();
            System.out.println("Current Time: "+stringdate);
            date = sdf.parse(stringdate);
            date2 = sdf.parse(data);
            
            if(date2.before(date))
            {
            System.out.println("Before: " + date2.compareTo(date));
            }
            else
            if(date2.equals(date))
            {
                
                //
                
                final MediaPlayer player = new MediaPlayer(media);
        MediaView view = new MediaView(player);
        
        System.out.println("media.width: "+media.getWidth());
        
        final DoubleProperty width = view.fitWidthProperty();
        final DoubleProperty height = view.fitHeightProperty();
        
        width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
        
        view.setPreserveRatio(true);
        
        root.getChildren().add(view);
        
        Scene scene = new Scene(root,400,400,Color.BLACK);
        
        view.isPreserveRatio();
        
        MediaControl mediaControl = new MediaControl(player);
        scene.setRoot(mediaControl);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
       //player.play();
        
        player.setOnReady(new Runnable()
        {
            @Override
            public void run()
            {
//                int w = player.getMedia().getWidth();
//                int h = player.getMedia().getHeight();
                
//                stage.setMinWidth(1336);
//                stage.setMaxHeight(700);
            }
        });
                
                //
                
            System.out.println("Equal: " + date2.compareTo(date));
            }
            else
            if(date2.after(date))
            {
            System.out.println("AFter ...." + date2.compareTo(date));
            }
            
                
            
            System.out.println("My Time: "+date);
            System.out.println("Word File: "+date2);
            
            
            
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }
            
            
            
            
            
            
           // 
            
            
        
    }
    EmbProcess ep;
    }