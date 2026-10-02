package me.kiiya.dcontent.blocks;

import java.util.List;

import me.kiiya.dcontent.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class CrateBlockEntity extends RandomizableContainerBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable("container.dungeonscontent.crate");
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;

    public CrateBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntityTypes.CRATE, worldPosition, blockState);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        this.openersCounter = new ContainerCounter();
    }

    protected void saveAdditional(final ValueOutput output) {
      super.saveAdditional(output);
      if (!this.trySaveLootTable(output)) {
        ContainerHelper.saveAllItems(output, this.items);
      }
    }

   protected void loadAdditional(final ValueInput input) {
      super.loadAdditional(input);
      this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      if (!this.tryLoadLootTable(input)) {
        ContainerHelper.loadAllItems(input, this.items);
      }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return ChestMenu.threeRows(containerId, inventory, this);
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    private void playSound(final BlockState state, final SoundEvent event) {
      Vec3i direction = (state.getValue(BarrelBlock.FACING)).getUnitVec3i();
      double x = (double)this.worldPosition.getX() + (double)0.5F + (double)direction.getX() / (double)2.0F;
      double y = (double)this.worldPosition.getY() + (double)0.5F + (double)direction.getY() / (double)2.0F;
      double z = (double)this.worldPosition.getZ() + (double)0.5F + (double)direction.getZ() / (double)2.0F;
      this.level.playSound(null, x, y, z, event, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    private void updateBlockState(final BlockState state, final boolean open) {
      this.level.setBlockAndUpdate(this.worldPosition, state.setValue(BarrelBlock.OPEN, open));
    }

    @Override
    public void startOpen(final ContainerUser user) {
      if (!this.remove && !user.getLivingEntity().isSpectator()) {
        this.openersCounter.incrementOpeners(user.getLivingEntity(), this.level, this.worldPosition, this.getBlockState(), user.getContainerInteractionRange());
      }
    }

    @Override
    public void stopOpen(final ContainerUser user) {
      if (!this.remove && !user.getLivingEntity().isSpectator()) {
        this.openersCounter.decrementOpeners(user.getLivingEntity(), this.level, this.worldPosition, this.getBlockState());
      }
    }

    public void recheckOpen() {
      if (!this.remove) {
        this.openersCounter.recheckOpeners(this.level, this.worldPosition, this.getBlockState());
      }
    }

    public List<ContainerUser> getEntitiesWithContainerOpen() {
      return this.openersCounter.getEntitiesWithContainerOpen(this.level, this.worldPosition);
    }

    private class ContainerCounter extends ContainerOpenersCounter {
        @Override
        public boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof ChestMenu menu && menu.getContainer() == CrateBlockEntity.this;
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState blockState) {
            playSound(blockState, SoundEvents.BARREL_CLOSE);
            updateBlockState(blockState, false);
        }

        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState blockState) {
            playSound(blockState, SoundEvents.BARREL_OPEN);
            updateBlockState(blockState, true);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState blockState, int previous, int current) {

        }
    }
}
