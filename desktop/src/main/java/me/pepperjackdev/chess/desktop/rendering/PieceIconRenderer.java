package me.pepperjackdev.chess.desktop.rendering;

import me.pepperjackdev.chess.core.Side;
import me.pepperjackdev.chess.core.piece.Piece;
import me.pepperjackdev.chess.core.piece.PieceType;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PieceIconRenderer {

    public static final PieceIconRenderer PIECE_ICON_RENDERER;

    static {
        PIECE_ICON_RENDERER = new PieceIconRenderer();
    }

    private static final int NUMBER_OF_PIECE_COLORS = 2;
    private static final int NUMBER_OF_PIECE_TYPES = 6;

    private static final String SPRITE_PATH = "pieces_sprite.svg";

    private final SVGDocument spriteDocument;
    private final Map<String, GraphicsNode> iconCache = new ConcurrentHashMap<>(); // Cache for GraphicsNode

    public PieceIconRenderer() {
        InputStream spriteFile = getClass().getResourceAsStream(SPRITE_PATH);
        if (spriteFile == null) {
            throw new IllegalArgumentException("Sprite file not found: " + SPRITE_PATH);
        }

        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);

        try {
            spriteDocument = factory.createSVGDocument(null, spriteFile);
        } catch (IOException e) {
            throw new RuntimeException("Malformed SVG sprite document", e);
        }
    }

    public void drawIcon(Graphics g, Piece piece, int x, int y, int width, int height) {
        try {
            // Generate a unique key for caching based on the piece and dimensions
            String cacheKey = piece.toString() + "-" + width + "x" + height;
            GraphicsNode graphicsNode = iconCache.computeIfAbsent(cacheKey, _ -> {
                try {
                    // Set up Batik rendering context
                    BridgeContext bridgeContext = new BridgeContext(new UserAgentAdapter());
                    bridgeContext.setDynamicState(BridgeContext.STATIC);
                    return new GVTBuilder().build(bridgeContext, spriteDocument);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to prepare graphics node for: " + piece, e);
                }
            });

            // Get the bounds of the piece in the sprite
            Rectangle bounds = getPieceBounds(piece);

            // Cast Graphics to Graphics2D for better control
            Graphics2D g2d = (Graphics2D) g;

            // Apply high-quality rendering hints
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            // Translate and scale to render the specific region of the sprite
            double scaleX = width / (double) bounds.width;
            double scaleY = height / (double) bounds.height;

            g2d.translate(x - bounds.x * scaleX, y - bounds.y * scaleY);
            g2d.scale(scaleX, scaleY);

            // Paint the piece icon directly using Batik
            graphicsNode.paint(g2d);
        } catch (Exception e) {
            throw new RuntimeException("Failed to render piece icon: " + piece, e);
        }
    }

    private Rectangle getPieceBounds(Piece piece) {
        int widthOfEveryPiece = (int) spriteDocument.getRootElement().getWidth().getBaseVal().getValue() / NUMBER_OF_PIECE_TYPES;
        int heightOfEveryPiece = (int) spriteDocument.getRootElement().getHeight().getBaseVal().getValue() / NUMBER_OF_PIECE_COLORS;

        return new Rectangle(
                widthOfEveryPiece * getPieceColumnIndex(piece.type()),
                heightOfEveryPiece * getPieceRowIndex(piece.side()),
                widthOfEveryPiece,
                heightOfEveryPiece
        );
    }

    private int getPieceColumnIndex(PieceType type) {
        return switch (type) {
            case KING -> 0;
            case QUEEN -> 1;
            case BISHOP -> 2;
            case KNIGHT -> 3;
            case ROOK -> 4;
            case PAWN -> 5;
        };
    }

    private int getPieceRowIndex(Side side) {
        return switch (side) {
            case WHITE -> 0;
            case BLACK -> 1;
        };
    }
}
