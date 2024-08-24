package net.viamcp.vialoadingbase.netty;

import com.viaversion.viaversion.api.connection.UserConnection;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.viamcp.vialoadingbase.netty.event.CompressionReorderEvent;
import net.viamcp.vialoadingbase.netty.handler.VLBViaDecodeHandler;
import net.viamcp.vialoadingbase.netty.handler.VLBViaEncodeHandler;

public abstract class VLBPipeline
extends ChannelInboundHandlerAdapter {
    public static final String VIA_DECODER_HANDLER_NAME = "via-decoder";
    public static final String VIA_ENCODER_HANDLER_NAME = "via-encoder";
    private final UserConnection user;

    public VLBPipeline(UserConnection user) {
        this.user = user;
    }

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        ctx.pipeline().addBefore(this.getDecoderHandlerName(), VIA_DECODER_HANDLER_NAME, (ChannelHandler)this.createVLBViaDecodeHandler());
        ctx.pipeline().addBefore(this.getEncoderHandlerName(), VIA_ENCODER_HANDLER_NAME, (ChannelHandler)this.createVLBViaEncodeHandler());
    }

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof CompressionReorderEvent) {
            int decoderIndex = ctx.pipeline().names().indexOf(this.getDecompressionHandlerName());
            if (decoderIndex == -1) {
                return;
            }
            if (decoderIndex > ctx.pipeline().names().indexOf(VIA_DECODER_HANDLER_NAME)) {
                ChannelHandler decoder = ctx.pipeline().get(VIA_DECODER_HANDLER_NAME);
                ChannelHandler encoder = ctx.pipeline().get(VIA_ENCODER_HANDLER_NAME);
                ctx.pipeline().remove(decoder);
                ctx.pipeline().remove(encoder);
                ctx.pipeline().addAfter(this.getDecompressionHandlerName(), VIA_DECODER_HANDLER_NAME, decoder);
                ctx.pipeline().addAfter(this.getCompressionHandlerName(), VIA_ENCODER_HANDLER_NAME, encoder);
            }
        }
    }

    public VLBViaDecodeHandler createVLBViaDecodeHandler() {
        return new VLBViaDecodeHandler(this.user);
    }

    public VLBViaEncodeHandler createVLBViaEncodeHandler() {
        return new VLBViaEncodeHandler(this.user);
    }

    public abstract String getDecoderHandlerName();

    public abstract String getEncoderHandlerName();

    public abstract String getDecompressionHandlerName();

    public abstract String getCompressionHandlerName();

    public UserConnection getUser() {
        return this.user;
    }
}

