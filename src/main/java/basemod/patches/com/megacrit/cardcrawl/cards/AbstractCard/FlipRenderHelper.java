package basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

public class FlipRenderHelper
{
    @SpirePatch(
        cls = "com.megacrit.cardcrawl.cards.AbstractCard",
        method = "renderHelper",
        paramtypes = {"com.badlogic.gdx.graphics.g2d.SpriteBatch",
            "com.badlogic.gdx.graphics.Color",
            "com.badlogic.gdx.graphics.Texture",
            "float",
            "float"
        }
    )
    public static class Helper1
    {
        public static void Replace(AbstractCard __instance, SpriteBatch sb, Color color, Texture img, float drawX, float drawY)
        {
            sb.setColor(color);
            sb.draw(img, drawX, drawY, 256, 260, 512, 512,
                    __instance.drawScale * Settings.scale * AddFlipVariables.getFlipScale(__instance), __instance.drawScale * Settings.scale,
                    __instance.angle,
                    0, 0, 512, 512, false, false);
        }
    }


    @SpirePatch(
            cls = "com.megacrit.cardcrawl.cards.AbstractCard",
            method = "renderHelper",
            paramtypes = {"com.badlogic.gdx.graphics.g2d.SpriteBatch",
                    "com.badlogic.gdx.graphics.Color",
                    "com.badlogic.gdx.graphics.Texture",
                    "float",
                    "float",
                    "float"
            }
    )
    public static class Helper2
    {
        public static void Replace(AbstractCard __instance, SpriteBatch sb, Color color, Texture img, float drawX, float drawY, float scale)
        {
            sb.setColor(color);
            sb.draw(img, drawX, drawY, 256, 260, 512, 512,
                    __instance.drawScale * Settings.scale * scale * AddFlipVariables.getFlipScale(__instance), __instance.drawScale * Settings.scale * scale,
                    __instance.angle,
                    0, 0, 512, 512, false, false);
        }
    }
}
