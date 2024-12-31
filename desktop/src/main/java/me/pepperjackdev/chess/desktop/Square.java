package me.pepperjackdev.chess.desktop;

import me.pepperjackdev.chess.core.position.Position;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Square
    extends JPanel {

    public Square(Position position) {
        setBackground(
                (position.getRank() + position.getFile()) % 2 == 0 ?
                        Color.BLACK : Color.WHITE
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            // renderSVG(g, "black_king.svg", getWidth(), getHeight());
            renderSVG(g, "black_king.svg", getWidth(), getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderSVG(Graphics g, String svgPath, int width, int height) throws IOException {
        InputStream svgFile = getClass().getResourceAsStream(svgPath);
        if (svgFile == null) {
            throw new IOException("SVG file not found: " + svgPath);
        }

        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        SVGDocument document = factory.createSVGDocument(null, svgFile);

        // Prepare the Batik rendering context
        UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
        BridgeContext bridgeContext = new BridgeContext(userAgentAdapter);
        bridgeContext.setDynamicState(BridgeContext.STATIC);

        GraphicsNode graphicsNode = new GVTBuilder().build(bridgeContext, document);

        // Scale and render the SVG
        Graphics2D g2d = (Graphics2D) g;
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        double scaleX = width / document.getRootElement().getWidth().getBaseVal().getValue();
        double scaleY = height / document.getRootElement().getHeight().getBaseVal().getValue();

        g2d.scale(scaleX, scaleY);
        graphicsNode.paint(g2d);
    }

    public void renderPNG(Graphics g, String pngPath, int width, int height) throws IOException {
        InputStream pngFile = getClass().getResourceAsStream(pngPath);
        if (pngFile == null) {
            throw new IOException("PNG file not found: " + pngPath);
        }

        // Load the PNG image
        Image image = ImageIO.read(pngFile);

        // Prepare Graphics2D for high-quality rendering
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set high-quality interpolation for smooth scaling
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        // Set rendering hint for high-quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Calculate the scale factor while maintaining the aspect ratio
        double scaleX = (double) width / image.getWidth(this);
        double scaleY = (double) height / image.getHeight(this);
        double scale = Math.min(scaleX, scaleY);  // Maintain aspect ratio

        // Apply scaling
        g2d.scale(scale, scale);

        // Draw the image centered
        int x = (int) ((getWidth() - image.getWidth(this) * scale) / 2);
        int y = (int) ((getHeight() - image.getHeight(this) * scale) / 2);

        g2d.drawImage(image, x, y, this);
    }


}
