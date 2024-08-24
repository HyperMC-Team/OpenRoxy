package net.viamcp.viamcp.util;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;

public class NettyUtil {
    public static ChannelPipeline decodeEncodePlacement(ChannelPipeline instance, String base, String newHandler, ChannelHandler handler) {
        switch (base) {
            case "decoder": {
                if (instance.get("via-decoder") == null) break;
                base = "via-decoder";
                break;
            }
            case "encoder": {
                if (instance.get("via-encoder") == null) break;
                base = "via-encoder";
            }
        }
        return instance.addBefore(base, newHandler, handler);
    }
}

