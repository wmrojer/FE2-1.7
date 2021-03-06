package forestryextras.items.bees.flowers;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IFlower;
import forestry.api.genetics.IIndividual;

public class FlowerUnstable extends FlowerProvider{
	public FlowerUnstable(String name){
		super(name);
		this.name = name;
	    AlleleManager.alleleRegistry.registerAllele(this);
	}
	
	@Override
	public boolean isAcceptedFlower(World world, IIndividual individual, int x, int y, int z, Block block){
		return Block.getBlockFromItem(OreDictionary.getOres("blockUnstable").get(0).getItem()) == block;
	}
	
	@Override
	public List<IFlower> getFlowers()
	{
		return null;
	}
}