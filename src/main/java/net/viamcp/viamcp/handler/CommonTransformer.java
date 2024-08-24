package net.viamcp.viamcp.handler;

import com.viaversion.viaversion.util.PipelineUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.lang.reflect.InvocationTargetException;

public class CommonTransformer {
    public static final String HANDLER_DECODER_NAME = "via-decoder";
    public static final String HANDLER_ENCODER_NAME = "via-encoder";

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void decompress(ChannelHandlerContext ctx, ByteBuf buf) throws InvocationTargetException {
        ChannelHandler handler = ctx.pipeline().get("decompress");
        ByteBuf decompressed = handler instanceof MessageToMessageDecoder ? (ByteBuf)PipelineUtil.callDecode((MessageToMessageDecoder)((MessageToMessageDecoder)handler), (ChannelHandlerContext)ctx, (Object)buf).get(0) : (ByteBuf)PipelineUtil.callDecode((ByteToMessageDecoder)((ByteToMessageDecoder)handler), (ChannelHandlerContext)ctx, (Object)buf).get(0);
        try {
            buf.clear().writeBytes(decompressed);
        }
        finally {
            decompressed.release();
        }
    }

    public static void compress(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        ByteBuf compressed = ctx.alloc().buffer();
        try {
            PipelineUtil.callEncode((MessageToByteEncoder)((MessageToByteEncoder)ctx.pipeline().get("compress")), (ChannelHandlerContext)ctx, (Object)buf, (ByteBuf)compressed);
            buf.clear().writeBytes(compressed);
        }
        finally {
            compressed.release();
        }
    }
}

