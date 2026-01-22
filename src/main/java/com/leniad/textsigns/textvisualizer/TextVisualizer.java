package com.leniad.textsigns.textvisualizer;

import com.hypixel.hytale.protocol.packets.interface_.CustomUICommand;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.Value;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;

import com.hypixel.hytale.server.core.ui.PatchStyle;

import javax.annotation.Nonnull;

public class TextVisualizer extends CustomUIHud {
    private String text = "";
    private boolean shouldDisplay = false;

    public TextVisualizer(@Nonnull PlayerRef playerRef, String text, boolean shouldDisplay) {
        super(playerRef);
        this.text = text;
        this.shouldDisplay = shouldDisplay;
    }

    public void updateUI() {
        UICommandBuilder newUi = new UICommandBuilder();
        this.setCommands(newUi);
        this.update(false, newUi);
    }

    public void setDisplay(String text, boolean active) {
        this.text = text;
        this.shouldDisplay = active;
        this.updateUI();
    }

    protected void build(@Nonnull UICommandBuilder ui) {
        ui.append("Pages/TextSigns/TextVisualizer.ui");
        this.setCommands(ui);
    }

    private void setCommands(@Nonnull UICommandBuilder ui) {
        PatchStyle bg = new PatchStyle().setBorder(Value.of(20)).setTexturePath(Value.of("Common/TooltipDefaultBackground.png"));


        if (!this.shouldDisplay) {
            ui.set("#Content.Background.Color", "#ffffff00");
        } else {
            ui.setObject("#Content.Background", bg);
        }

        ui.set("#SignContent.Text", text);
    }
}
