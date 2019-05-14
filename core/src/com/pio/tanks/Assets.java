package com.pio.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Assets
{
    public static final Texture SHELL_TEX_0 = new Texture("tank_bullet3.png");
    public static final Texture SHELL_TEX_1 = new Texture("tank_bullet4.png");
    public static final Texture TANK_TEX = new Texture("tanks_tankGreen_body1.png");
    public static final Texture TURRET_TEX = new Texture("tanks_turret1.png");
    public static final Texture BG_TEX = new Texture("uncolored_hills.png");
    public static final Texture[] CLOUD_TEX = {new Texture("cloud1.png"),
            new Texture("cloud4.png"), new Texture("cloud5.png"), new Texture("cloud6.png")};

    public static final Label.LabelStyle FONT = loadFont();
    public static Label.LabelStyle loadFont()
    {
        FreeTypeFontGenerator fontGenerator =
                new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 1.6f;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = Texture.TextureFilter.Linear;
        fontParameters.magFilter = Texture.TextureFilter.Linear;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontGenerator.generateFont(fontParameters);
        return labelStyle;
    }
}
