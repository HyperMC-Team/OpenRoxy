package Rename;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;
import net.minecraft.network.PacketBuffer;

public enum Class479 implements Class476<Class479>
{
    START{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            cons.accept(HELLO);
        }
    }
    ,
    HELLO{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            cons.accept(WAITINGSERVERDATA);
            Class482.INSTANCE.getForgeChannel().sendToServer("REGISTER", new PacketBuffer(Unpooled.buffer().writeBytes(Class482.HYT_REGISTER)));
            PacketBuffer helloBuffer = new PacketBuffer(Unpooled.buffer());
            helloBuffer.writeByte(1);
            helloBuffer.writeByte(2);
            Class482.INSTANCE.getForgeChannel().sendToServer("FML|HS", helloBuffer);
            Class482.INSTANCE.getForgeChannel().sendToServer("FML|HS", new PacketBuffer(Unpooled.buffer().writeBytes(Class482.MOD_LIST)));
        }
    }
    ,
    WAITINGSERVERDATA{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            cons.accept(WAITINGSERVERCOMPLETE);
            Class482.INSTANCE.getForgeChannel().sendToServer("FML|HS", new PacketBuffer(Unpooled.buffer().writeByte(-1).writeByte(2)));
        }
    }
    ,
    WAITINGSERVERCOMPLETE{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            new HashMap();
            new HashSet();
            boolean hasMore = payload.readBoolean();
            if (hasMore) {
                cons.accept(WAITINGSERVERCOMPLETE);
            } else {
                cons.accept(PENDINGCOMPLETE);
                Class482.INSTANCE.getForgeChannel().sendToServer("FML|HS", new PacketBuffer(Unpooled.buffer().writeByte(-1).writeByte(3)));
            }
        }
    }
    ,
    PENDINGCOMPLETE{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            cons.accept(COMPLETE);
            Class482.INSTANCE.getForgeChannel().sendToServer("FML|HS", new PacketBuffer(Unpooled.buffer().writeByte(-1).writeByte(4)));
        }
    }
    ,
    COMPLETE{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            cons.accept(DONE);
            Class482.INSTANCE.getForgeChannel().sendToServer("FML|HS", new PacketBuffer(Unpooled.buffer().writeByte(-1).writeByte(5)));
        }
    }
    ,
    DONE{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
            if (id == -2) {
                cons.accept(HELLO);
            }
        }
    }
    ,
    ERROR{

        @Override
        public void accept(int id, ByteBuf payload, Consumer<? super Class479> cons) {
        }
    }

}

