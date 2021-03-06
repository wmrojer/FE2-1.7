package forestryextras.items.bees.effects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IEffectData;

public class EffectPureDaisy extends BeeEffect{

	public EffectPureDaisy(String name) {
		super(name);
		this.name = name;
	}
	
	@Override
	public IEffectData doEffect(IBeeGenome genome, IEffectData storedData,
			IBeeHousing housing, World world, int x, int y, int z){
		int min = 1;
		int max = 100;
		Random rand = new Random();
	    int number = rand.nextInt((max - min) + 1) + min;

	    if(number == 1){
			for(int xx = -4; xx < 4; xx++){
				for(int zz = -4; zz < 4; zz++){
					Block block = world.getBlock(x + xx, y, z + zz);
					if(block != null){
						ItemStack stack = new ItemStack(Item.getItemFromBlock(block), 1, world.getBlockMetadata(x + xx, y, z + zz));
						Block output = getOut(stack, block);
						if(output != null){
							world.setBlock(x + xx, y, z + zz, output);
							for(int i = 0; i < 25; i++) {
								double xX = xx + Math.random();
								double yY = y + Math.random() + 0.5;
								double zZ = zz + Math.random();

								Botania.proxy.wispFX(world, x + xX, yY, z + zZ, 1F, 1F, 1F, (float) Math.random() / 2F);
							}
							xx = 4;
							zz = 4;
							continue;
						}
					}
				}
			}
	    }
	    return null;
	}
	
	public Block getOut(ItemStack stack, Block block){		
		if(isOreDict(stack, "stone"))
			return ModBlocks.livingrock;
		else if(isOreDict(stack, "logWood") || block == Blocks.log || block == Blocks.log2)
			return ModBlocks.livingwood;
		else
			return null;
	}
	
	/** This part of the code is mostly from Vazkii's Botania, used it since this is a bee integrating with it. */
	public boolean isOreDict(ItemStack stack, String entry) {
		for(ItemStack s : OreDictionary.getOres(entry)){
			ItemStack copy = s.copy();
				
			if(stack.isItemEqual(copy))
				return true;
		}
		return false;
	}
}