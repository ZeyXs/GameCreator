package fr.zeyx.gamecreator.functionblocks;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class FunctionBlockHandler {

    public boolean isFunctionBlock(Location location) {
        return true;
    }

    public boolean isFunctionBlock(Block block) {
        return isFunctionBlock(block.getLocation());
    }

    public void placeFunctionBlock(Location location) {
        // TODO
    }

    public void removeFunctionBlock(Location location) {
        // TODO
    }

    public void loadFunctionBlock(Chunk chunk) {
        // TODO
    }

    public void saveFunctionBlock(Chunk chunk) {
        // TODO
    }

}
