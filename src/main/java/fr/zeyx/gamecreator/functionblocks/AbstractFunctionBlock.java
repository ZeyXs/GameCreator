package fr.zeyx.gamecreator.functionblocks;

import org.bukkit.Location;
import org.bukkit.Material;

public abstract class AbstractFunctionBlock {

    public String getId() {
        return null;
    }

    public Location getLocation() {
        return null;
    }

    public String getFunctionBlockType() {
        return null;
    }

    public String getData() {
        return null;
    }

    public Material getMaterial() {
        return Material.BEDROCK;
    }

    public abstract void tick();

    public abstract void execute();

}
