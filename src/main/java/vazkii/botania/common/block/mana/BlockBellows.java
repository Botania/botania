/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Aug 28, 2015, 5:18:13 PM (GMT)]
 */
package vazkii.botania.common.block.mana;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibBlockNames;

public class BlockBellows extends BlockModContainer implements ILexiconable {

	private static final int[] META_ROTATIONS = new int[] { 3, 4, 2, 5 };

	public BlockBellows() {
		super(Material.wood);
		setHardness(2.0F);
		setStepSound(soundTypeWood);
		setUnlocalizedName(LibBlockNames.BELLOWS);

		float f = (1F - 10 / 16F) / 2F;
		setBlockBounds(f, 0F, f, 1F - f, 10F / 16F, 1F - f);
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, META_ROTATIONS[l], 2);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		// NO-OP
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing s, float xs, float ys, float zs) {
		if(EntityDoppleganger.isTruePlayer(player))
			((TileBellows) world.getTileEntity(pos)).interact();
		return true;
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
	public IIcon getIcon(int par1, int par2) {
		return ModBlocks.livingwood.getIcon(par1, 0);
	}

	@Override
	public int getRenderType() {
		return LibRenderIDs.idBellows;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBellows();
	}

	@Override
	public LexiconEntry getEntry(World world, BlockPos pos, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.bellows;
	}

}
