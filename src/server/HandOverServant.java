package server;

import ToFileHandOver.HandOverPOA;
import org.omg.CORBA.ORB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

public class HandOverServant extends HandOverPOA {
    public static final String FROM = "from.txt";
    public static final String TO = "to.txt";

    private ORB orb;

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    @Override
    public String handOver() {
        try (FileInputStream fis = new FileInputStream(FROM);
             FileOutputStream fos = new FileOutputStream(TO, true);
             FileChannel channel = fis.getChannel()) {
            channel.transferTo(0, channel.size(), Channels.newChannel(fos));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    @Override
    public void handOver(String newHandOver) {
        throw new UnsupportedOperationException();
    }
}
