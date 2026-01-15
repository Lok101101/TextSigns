package com.leniad.textsigns.deletesign;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockType;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.leniad.textsigns.SignTextsRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class DeleteSign extends EntityEventSystem<EntityStore, BreakBlockEvent> {

    @Nonnull
    private final Query<EntityStore> query;

    public DeleteSign(@Nonnull Class<BreakBlockEvent> eventType) {
        super(eventType);

        this.query = Query.and(new Query[]{Player.getComponentType()});
    }

    @Override
    public void handle(int i, @NotNull ArchetypeChunk<EntityStore> archetypeChunk, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> commandBuffer, @NotNull BreakBlockEvent breakBlockEvent) {
        SignTextsRegistry res = store.getResource(SignTextsRegistry.getResourceType());
        res.delete(breakBlockEvent.getTargetBlock());
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return this.query;
    }
}
