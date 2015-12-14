package com.wpi.cs509.teamA.util.PaintHelper;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.LinearTransform;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by cuixi on 12/14/2015.
 */
public class PaintImageHelper extends PaintHelperBasics{

    static private MainModel model = null;

    public static BufferedImage paintImage(Path path, LinearTransform lt) {
        BufferedImage image = path.getMap().getImage();

        BufferedImage bi = PaintHelperBasics.resize(image, (int)(image.getWidth()*lt.getScale()), (int)(image.getHeight()*lt.getScale()));
        Graphics2D g2 = bi.createGraphics();

        paintMultiMaps(g2, path, lt);
        return bi;
    }
    public static synchronized void printRoute(Path path, LinearTransform plt, File file, int number) {
        if (null == path || path.getNodes().size() == 0) {

            return;
        }
        ImageComponent imageComponent = ViewManager.getImageComponent();
        BufferedImage bi = paintImage(path, plt);

        try {
            System.out.println(file);
            ImageIO.write(bi, "PNG", new File(file+"\\"+"Step"+number+"_"+path.getMap().getImageName()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void restorePrintMap(GeneralMap originalMap, float scale, int imageXpos, int imageYpos) {
        model.setCurrentMap(originalMap);
        model.getCurrentMap().setDisplayScale(scale);
        ViewManager.getImageComponent().setImageXpos(imageXpos);
        ViewManager.getImageComponent().setImageYpos(imageYpos);
    }


    public static void paintMultiMaps(Graphics2D g2, Path path, LinearTransform plt) {
//        setPrintMap(map, 1, 0, 0);
        if (null == path || path.getNodes().size() == 0) {

            return;
        }
        PaintHelperComposite.paintPath(path, g2, plt);

        PaintHelperComposite.paintNodes(path.getNodes(), g2, PaintHelperBasics.DrawStyleEnum.BasicNode, plt);
        PaintHelperComposite.paintStartEndNode(g2, plt);

//        PaintHelperBasics.restorePrintMap(originalMap, scale, imageXpos, imageYpos);

    }


    public static void setPrintMap(GeneralMap map, float scale, int imageXpos, int imageYpos) {
        model.setCurrentMap(map);
        model.getCurrentMap().setDisplayScale(scale);
        ViewManager.getImageComponent().setImageXpos(imageXpos);
        ViewManager.getImageComponent().setImageYpos(imageYpos);
    }



    public static void setModel(MainModel model) {
        PaintImageHelper.model = model;
    }
}
