/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Feb 18, 2014, 10:13:02 PM (GMT)]
 */
package vazkii.botania.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.crafting.IInfusionStabiliser", striprefs = true)
public class BlockPylon extends BlockModContainer implements ILexiconable, IInfusionStabiliser {

	public BlockPylon() {
		super(Material.iron);
		setHardness(5.5F);
		setStepSound(soundTypeMetal);
		setUnlocalizedName(LibBlockNames.PYLON);
		setLightLevel(0.5F);

		float f = 1F / 16F * 2F;
		setBlockBounds(f, 0F, f, 1F - f, 1F / 16F * 21F, 1F - f);
	}
	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public Block setUnlocalizedName(String par1Str) {
		GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
		for(int i = 0; i < 3; i++)
			par3.add(new ItemStack(par1, 1, i));
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return par2 == 0 ? Blocks.diamond_block.getIcon(0, 0) : ModBlocks.storage.getIcon(0, par2);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idPylon;
	}

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos) {
		return world.getBlockMetadata(x, y, z) == 0 ? 8 : 15;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TilePylon();
	}

	@Override
	public LexiconEntry getEntry(World world, BlockPos pos, EntityPlayer player, ItemStack lexicon) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 0 ? LexiconData.pylon : meta == 1 ? LexiconData.alfhomancyIntro : LexiconData.gaiaRitual;
	}
	@Override
	public boolean canStabaliseInfusion(World world, BlockPos pos) {
		return true;
	}
}
