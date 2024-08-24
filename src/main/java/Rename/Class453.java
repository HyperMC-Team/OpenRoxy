package Rename;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class Class453 {
    static final boolean DEBUG_MODE = false;

    public static GifImage read(byte[] in) throws IOException {
        Class453 decoder = new Class453();
        GifImage img = new GifImage(decoder);
        GifFrame frame = null;
        int pos = Class453.readHeader(in, img);
        pos = Class453.readLogicalScreenDescriptor(img, in, pos);
        if (img.hasGlobColTbl) {
            img.globalColTbl = new int[img.sizeOfGlobColTbl];
            pos = Class453.readColTbl(in, img.globalColTbl, pos);
        }
        block11: while (pos < in.length) {
            int block = in[pos] & 0xFF;
            switch (block) {
                case 33: {
                    if (pos + 1 >= in.length) {
                        throw new IOException("Unexpected end of file.");
                    }
                    switch (in[pos + 1] & 0xFF) {
                        case 254: {
                            pos = Class453.readTextExtension(in, pos);
                            continue block11;
                        }
                        case 255: {
                            pos = Class453.readAppExt(img, in, pos);
                            continue block11;
                        }
                        case 1: {
                            frame = null;
                            pos = Class453.readTextExtension(in, pos);
                            continue block11;
                        }
                        case 249: {
                            if (frame == null) {
                                frame = new GifFrame(decoder);
                                img.frames.add(frame);
                            }
                            pos = Class453.readGraphicControlExt(frame, in, pos);
                            continue block11;
                        }
                    }
                    throw new IOException("Unknown extension at " + pos);
                }
                case 44: {
                    if (frame == null) {
                        frame = new GifFrame(decoder);
                        img.frames.add(frame);
                    }
                    pos = Class453.readImgDescr(frame, in, pos);
                    if (frame.hasLocColTbl) {
                        GifFrame.access$1802(frame, new int[frame.sizeOfLocColTbl]);
                        pos = Class453.readColTbl(in, frame.localColTbl, pos);
                    }
                    pos = Class453.readImgData(frame, in, pos);
                    frame = null;
                    continue block11;
                }
                case 59: {
                    return img;
                }
            }
            double progress = (double) pos / (double)in.length;
            if (progress < 0.9) {
                throw new IOException("Unknown block at: " + pos);
            }
            pos = in.length;
        }
        return img;
    }

    public static GifImage read(InputStream is) throws IOException {
        byte[] data = new byte[is.available()];
        is.read(data, 0, data.length);
        return Class453.read(data);
    }

    static int readAppExt(GifImage img, byte[] in, int i) {
        img.appId = new String(in, i + 3, 8);
        img.appAuthCode = new String(in, i + 11, 3);
        int subBlockSize = in[i += 14] & 0xFF;
        if (subBlockSize == 3) {
            img.repetitions = in[i + 2] & 0xFF | in[i + 3] & 0xFF00;
            return i + 5;
        }
        while ((in[i] & 0xFF) != 0) {
            i += (in[i] & 0xFF) + 1;
        }
        return i + 1;
    }

    static int readColTbl(byte[] in, int[] colors, int i) {
        int numColors = colors.length;
        for (int c = 0; c < numColors; ++c) {
            int a = 255;
            int r = in[i++] & 0xFF;
            int g = in[i++] & 0xFF;
            int b = in[i++] & 0xFF;
            colors[c] = ((0xFF00 | r) << 8 | g) << 8 | b;
        }
        return i;
    }

    static int readGraphicControlExt(GifFrame fr, byte[] in, int i) {
        fr.disposalMethod = (in[i + 3] & 0x1C) >>> 2;
        fr.transpColFlag = (in[i + 3] & 1) == 1;
        fr.delay = in[i + 4] & 0xFF | (in[i + 5] & 0xFF) << 8;
        fr.transpColIndex = in[i + 6] & 0xFF;
        return i + 8;
    }

    static int readHeader(byte[] in, GifImage img) throws IOException {
        if (in.length < 6) {
            throw new IOException("Image is truncated.");
        }
        img.header = new String(in, 0, 6);
        if (!img.header.equals("GIF87a") && !img.header.equals("GIF89a")) {
            throw new IOException("Invalid GIF header.");
        }
        return 6;
    }

    static int readImgData(GifFrame fr, byte[] in, int i) {
        int fileSize = in.length;
        int minCodeSize = in[i++] & 0xFF;
        int clearCode = 1 << minCodeSize;
        fr.firstCodeSize = minCodeSize + 1;
        fr.clearCode = clearCode;
        fr.endOfInfoCode = clearCode + 1;
        int imgDataSize = Class453.readImgDataSize(in, i);
        byte[] imgData = new byte[imgDataSize + 2];
        int imgDataPos = 0;
        int subBlockSize = in[i] & 0xFF;
        while (subBlockSize > 0) {
            try {
                int nextSubBlockSizePos = i + subBlockSize + 1;
                int nextSubBlockSize = in[nextSubBlockSizePos] & 0xFF;
                System.arraycopy(in, i + 1, imgData, imgDataPos, subBlockSize);
                imgDataPos += subBlockSize;
                i = nextSubBlockSizePos;
                subBlockSize = nextSubBlockSize;
            }
            catch (Exception e) {
                subBlockSize = fileSize - i - 1;
                System.arraycopy(in, i + 1, imgData, imgDataPos, subBlockSize);
                imgDataPos += subBlockSize;
                i += subBlockSize + 1;
                break;
            }
        }
        GifFrame.access$702(fr, imgData);
        return ++i;
    }

    static int readImgDataSize(byte[] in, int i) {
        int fileSize = in.length;
        int imgDataPos = 0;
        int subBlockSize = in[i] & 0xFF;
        while (subBlockSize > 0) {
            try {
                int nextSubBlockSizePos = i + subBlockSize + 1;
                int nextSubBlockSize = in[nextSubBlockSizePos] & 0xFF;
                imgDataPos += subBlockSize;
                i = nextSubBlockSizePos;
                subBlockSize = nextSubBlockSize;
            }
            catch (Exception e) {
                subBlockSize = fileSize - i - 1;
                imgDataPos += subBlockSize;
                break;
            }
        }
        return imgDataPos;
    }

    static int readImgDescr(GifFrame fr, byte[] in, int i) {
        byte b;
        fr.x = in[++i] & 0xFF | (in[++i] & 0xFF) << 8;
        fr.y = in[++i] & 0xFF | (in[++i] & 0xFF) << 8;
        fr.w = in[++i] & 0xFF | (in[++i] & 0xFF) << 8;
        fr.h = in[++i] & 0xFF | (in[++i] & 0xFF) << 8;
        fr.wh = fr.w * fr.h;
        fr.hasLocColTbl = ((b = in[++i]) & 0x80) >>> 7 == 1;
        fr.interlaceFlag = (b & 0x40) >>> 6 == 1;
        fr.sortFlag = (b & 0x20) >>> 5 == 1;
        int colTblSizePower = (b & 7) + 1;
        fr.sizeOfLocColTbl = 1 << colTblSizePower;
        return ++i;
    }

    static int readLogicalScreenDescriptor(GifImage img, byte[] in, int i) {
        img.w = in[i] & 0xFF | (in[i + 1] & 0xFF) << 8;
        img.h = in[i + 2] & 0xFF | (in[i + 3] & 0xFF) << 8;
        img.wh = img.w * img.h;
        byte b = in[i + 4];
        img.hasGlobColTbl = (b & 0x80) >>> 7 == 1;
        int colResPower = ((b & 0x70) >>> 4) + 1;
        img.colorResolution = 1 << colResPower;
        img.sortFlag = (b & 8) >>> 3 == 1;
        int globColTblSizePower = (b & 7) + 1;
        img.sizeOfGlobColTbl = 1 << globColTblSizePower;
        img.bgColIndex = in[i + 5] & 0xFF;
        img.pxAspectRatio = in[i + 6] & 0xFF;
        return i + 7;
    }

    static int readTextExtension(byte[] in, int pos) {
        int i = pos + 2;
        int subBlockSize = in[i++] & 0xFF;
        while (subBlockSize != 0 && i < in.length) {
            i += subBlockSize;
            subBlockSize = in[i++] & 0xFF;
        }
        return i;
    }

    public static final class GifImage {
        public String header;
        private int w;
        private int h;
        private int wh;
        public boolean hasGlobColTbl;
        public int colorResolution;
        public boolean sortFlag;
        public int sizeOfGlobColTbl;
        public int bgColIndex;
        public int pxAspectRatio;
        public int[] globalColTbl;
        private final List<GifFrame> frames = new ArrayList<GifFrame>(64);
        public String appId = "";
        public String appAuthCode = "";
        public int repetitions = 0;
        private BufferedImage img = null;
        private final BitReader bits = new BitReader();
        private final CodeTable codes = new CodeTable();
        private Graphics2D g;

        public GifImage(Class453 this$0) {
        }

        private int[] decode(GifFrame fr, int[] activeColTbl) {
            this.codes.init(fr, activeColTbl, this.bits);
            this.bits.init(fr.data);
            int clearCode = fr.clearCode;
            int endCode = fr.endOfInfoCode;
            int[] out = new int[this.wh];
            int[][] tbl = this.codes.table;
            int outPos = 0;
            this.codes.clear();
            this.bits.read();
            int code = this.bits.read();
            int[] pixels = tbl[code];
            System.arraycopy(pixels, 0, out, outPos, pixels.length);
            outPos += pixels.length;
            try {
                while (true) {
                    int prevCode = code;
                    code = this.bits.read();
                    if (code == clearCode) {
                        this.codes.clear();
                        code = this.bits.read();
                        pixels = tbl[code];
                        System.arraycopy(pixels, 0, out, outPos, pixels.length);
                        outPos += pixels.length;
                        continue;
                    }
                    if (code != endCode) {
                        int[] prevVals = tbl[prevCode];
                        int[] prevValsAndK = new int[prevVals.length + 1];
                        System.arraycopy(prevVals, 0, prevValsAndK, 0, prevVals.length);
                        if (code < this.codes.nextCode) {
                            pixels = tbl[code];
                            System.arraycopy(pixels, 0, out, outPos, pixels.length);
                            outPos += pixels.length;
                            prevValsAndK[prevVals.length] = tbl[code][0];
                        } else {
                            prevValsAndK[prevVals.length] = prevVals[0];
                            System.arraycopy(prevValsAndK, 0, out, outPos, prevValsAndK.length);
                            outPos += prevValsAndK.length;
                        }
                        this.codes.add(prevValsAndK);
                        continue;
                    }
                    break;
                }
            }
            catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                // empty catch block
            }
            return out;
        }

        private int[] deinterlace(int[] src, GifFrame fr) {
            int w = fr.w;
            int h = fr.h;
            int wh = fr.wh;
            int[] dest = new int[src.length];
            int set2Y = h + 7 >>> 3;
            int set3Y = set2Y + (h + 3 >>> 3);
            int set4Y = set3Y + (h + 1 >>> 2);
            int set2 = w * set2Y;
            int set3 = w * set3Y;
            int set4 = w * set4Y;
            int w2 = w << 1;
            int w4 = w2 << 1;
            int w8 = w4 << 1;
            int from = 0;
            int to = 0;
            while (from < set2) {
                System.arraycopy(src, from, dest, to, w);
                from += w;
                to += w8;
            }
            to = w4;
            while (from < set3) {
                System.arraycopy(src, from, dest, to, w);
                from += w;
                to += w8;
            }
            to = w2;
            while (from < set4) {
                System.arraycopy(src, from, dest, to, w);
                from += w;
                to += w4;
            }
            to = w;
            while (from < wh) {
                System.arraycopy(src, from, dest, to, w);
                from += w;
                to += w2;
            }
            return dest;
        }

        private void drawFrame(GifFrame fr) {
            int[] activeColTbl = fr.hasLocColTbl ? fr.localColTbl : this.globalColTbl;
            int[] pixels = this.decode(fr, activeColTbl);
            if (fr.interlaceFlag) {
                pixels = this.deinterlace(pixels, fr);
            }
            BufferedImage frame = new BufferedImage(fr.w, fr.h, 2);
            System.arraycopy(pixels, 0, ((DataBufferInt)frame.getRaster().getDataBuffer()).getData(), 0, fr.wh);
            this.g.drawImage(frame, fr.x, fr.y, null);
            int[] prevPx = new int[this.wh];
            System.arraycopy(((DataBufferInt)this.img.getRaster().getDataBuffer()).getData(), 0, prevPx, 0, this.wh);
            fr.img = new BufferedImage(this.w, this.h, 2);
            System.arraycopy(prevPx, 0, ((DataBufferInt)fr.img.getRaster().getDataBuffer()).getData(), 0, this.wh);
            if (fr.disposalMethod == 2) {
                this.g.clearRect(fr.x, fr.y, fr.w, fr.h);
            } else if (fr.disposalMethod == 3) {
                System.arraycopy(prevPx, 0, ((DataBufferInt)this.img.getRaster().getDataBuffer()).getData(), 0, this.wh);
            }
        }

        public int getBackgroundColor() {
            GifFrame frame = this.frames.get(0);
            if (frame.hasLocColTbl) {
                return frame.localColTbl[this.bgColIndex];
            }
            if (this.hasGlobColTbl) {
                return this.globalColTbl[this.bgColIndex];
            }
            return 0;
        }

        public int getDelay(int index) {
            return this.frames.get(index).delay;
        }

        public BufferedImage getFrame(int index) {
            if (this.img == null) {
                this.img = new BufferedImage(this.w, this.h, 2);
                this.g = this.img.createGraphics();
                this.g.setBackground(new Color(0, true));
            }
            GifFrame fr = this.frames.get(index);
            if (fr.img == null) {
                for (int i = 0; i <= index; ++i) {
                    fr = this.frames.get(i);
                    if (fr.img != null) continue;
                    this.drawFrame(fr);
                }
            }
            return fr.img;
        }

        public int getFrameCount() {
            return this.frames.size();
        }

        public int getHeight() {
            return this.h;
        }

        public int getWidth() {
            return this.w;
        }
    }

    static final class GifFrame {
        private int disposalMethod;
        private boolean transpColFlag;
        private int delay;
        private int transpColIndex;
        private int x;
        private int y;
        private int w;
        private int h;
        private int wh;
        private boolean hasLocColTbl;
        private boolean interlaceFlag;
        private boolean sortFlag;
        private int sizeOfLocColTbl;
        private int[] localColTbl;
        private int firstCodeSize;
        private int clearCode;
        private int endOfInfoCode;
        private byte[] data;
        private BufferedImage img;

        GifFrame(Class453 this$0) {
        }

        static int[] access$1802(GifFrame x0, int[] x1) {
            x0.localColTbl = x1;
            return x1;
        }

        static byte[] access$702(GifFrame x0, byte[] x1) {
            x0.data = x1;
            return x1;
        }
    }

    static final class BitReader {
        private int nextBitToRead;
        private int numberOfBitsToRead;
        private int bitMask;
        private byte[] bytes;

        BitReader() {
        }

        private void init(byte[] bytes) {
            this.bytes = bytes;
            this.nextBitToRead = 0;
        }

        private int read() {
            int byteIndex = this.nextBitToRead >>> 3;
            int bitsToShiftRight = this.nextBitToRead & 7;
            int byte0 = this.bytes[byteIndex++] & 0xFF;
            int byte1 = this.bytes[byteIndex++] & 0xFF;
            int byte2 = this.bytes[byteIndex] & 0xFF;
            int buffer = ((byte2 << 8 | byte1) << 8 | byte0) >>> bitsToShiftRight;
            this.nextBitToRead += this.numberOfBitsToRead;
            return buffer & this.bitMask;
        }

        private void setNumberOfBitsToRead(int numberOfBitsToRead) {
            this.numberOfBitsToRead = numberOfBitsToRead;
            this.bitMask = (1 << numberOfBitsToRead) - 1;
        }
    }

    static final class CodeTable {
        private final int[][] table = new int[4096][1];
        private int initTableSize;
        private int initCodeSize;
        private int initCodeLimit;
        private int codeSize;
        private int nextCode;
        private int nextCodeLimit;
        private BitReader bitReader;

        CodeTable() {
        }

        private int add(int[] indices) {
            if (this.nextCode < 4096) {
                if (this.nextCode == this.nextCodeLimit && this.codeSize < 12) {
                    ++this.codeSize;
                    this.bitReader.setNumberOfBitsToRead(this.codeSize);
                    this.nextCodeLimit = (1 << this.codeSize) - 1;
                }
                this.table[this.nextCode++] = indices;
            }
            return this.codeSize;
        }

        private int clear() {
            this.codeSize = this.initCodeSize;
            this.bitReader.setNumberOfBitsToRead(this.codeSize);
            this.nextCodeLimit = this.initCodeLimit;
            this.nextCode = this.initTableSize;
            return this.codeSize;
        }

        private void init(GifFrame fr, int[] activeColTbl, BitReader br) {
            this.bitReader = br;
            int numColors = activeColTbl.length;
            this.initCodeSize = fr.firstCodeSize;
            this.initCodeLimit = (1 << this.initCodeSize) - 1;
            this.nextCode = this.initTableSize = fr.endOfInfoCode + 1;
            for (int c = numColors - 1; c >= 0; --c) {
                this.table[c][0] = activeColTbl[c];
            }
            this.table[fr.clearCode] = new int[]{fr.clearCode};
            this.table[fr.endOfInfoCode] = new int[]{fr.endOfInfoCode};
            if (fr.transpColFlag && fr.transpColIndex < numColors) {
                this.table[fr.transpColIndex][0] = 0;
            }
        }
    }
}

