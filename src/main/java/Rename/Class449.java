package Rename;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Class449
extends RandomAccessFile {
    private long currentPosition = 0L;

    public Class449(String fileName, String mode) throws FileNotFoundException {
        super(fileName, mode);
    }

    @Override
    public int read(byte[] byteArray) throws IOException {
        int bytesRead = super.read(byteArray);
        if (bytesRead != -1) {
            Class451.processData(byteArray, this.currentPosition);
            this.currentPosition += bytesRead;
        }
        return bytesRead;
    }

    public Class449(File file, String mode) throws FileNotFoundException {
        super(file, mode);
    }

    @Override
    public int read(byte[] byteArray, int offset, int length) throws IOException {
        int bytesRead = super.read(byteArray, offset, length);
        if (bytesRead != -1) {
            Class451.processData(byteArray, this.currentPosition, offset, length);
            this.currentPosition += bytesRead;
        }
        return bytesRead;
    }

    @Override
    public int read() throws IOException {
        int byteRead = super.read();
        if (byteRead != -1) {
            int processedByte = Class451.processByte(byteRead, this.currentPosition);
            ++this.currentPosition;
            return processedByte;
        }
        return byteRead;
    }

    @Override
    public void seek(long position) throws IOException {
        super.seek(position);
        this.currentPosition = position;
    }
}

