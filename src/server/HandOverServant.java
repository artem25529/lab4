package server;

import ToFileHandOver.HandOverPOA;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HandOverServant extends HandOverPOA {
    public static final String PATH = "file.txt";
    private ORB orb;

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    @Override
    public String handOver() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handOver(String newHandOver) {
        try {
            Files.write(Paths.get(PATH), newHandOver.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
