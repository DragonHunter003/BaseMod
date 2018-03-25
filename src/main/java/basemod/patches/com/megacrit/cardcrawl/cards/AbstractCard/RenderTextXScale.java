package basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class RenderTextXScale
{
    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="renderTitle"
    )
    public static class RenderTitle
    {
        public static ExprEditor Instrument()
        {
            return new ExprEditor()
            {
                @Override
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getClassName().equals("com.badlogic.gdx.graphics.g2d.BitmapFont$BitmapFontData") && m.getMethodName().equals("setScale")) {
                        AddFlipVariables.AddFields(m.getEnclosingClass());
                        m.replace("$_ = $proceed($1 * flipScale, $1);");
                    }
                }
            };
        }
    }

    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="getDescFont"
    )
    public static class GetDescFont
    {
        public static ExprEditor Instrument()
        {
            return new ExprEditor()
            {
                @Override
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getClassName().equals("com.badlogic.gdx.graphics.g2d.BitmapFont$BitmapFontData") && m.getMethodName().equals("setScale")) {
                        AddFlipVariables.AddFields(m.getEnclosingClass());
                        m.replace("$_ = $proceed($1 * flipScale, $1);");
                    }
                }
            };
        }
    }

    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="renderDescription"
    )
    public static class DescriptionStartX
    {
        @SpireInsertPatch(
                rloc=25,
                localvars={"start_x", "i"}
        )
        public static void Insert(AbstractCard __instance, SpriteBatch sb, @ByRef float[] start_x, int i)
        {
            start_x[0] = __instance.current_x - (__instance.description.get(i).width * __instance.drawScale * AddFlipVariables.getFlipScale(__instance) / 2.0f);
        }
    }

    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="getEnergyFont"
    )
    public static class GetEnergyFont
    {
        public static ExprEditor Instrument()
        {
            return new ExprEditor()
            {
                @Override
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getClassName().equals("com.badlogic.gdx.graphics.g2d.BitmapFont$BitmapFontData") && m.getMethodName().equals("setScale")) {
                        AddFlipVariables.AddFields(m.getEnclosingClass());
                        m.replace("$_ = $proceed($1 * flipScale, $1);");
                    }
                }
            };
        }
    }

    @SpirePatch(
            cls="com.megacrit.cardcrawl.cards.AbstractCard",
            method="renderEnergy"
    )
    public static class RenderEnergyPosition
    {
        public static ExprEditor Instrument()
        {
            return new ExprEditor()
            {
                @Override
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getClassName().equals("com.megacrit.cardcrawl.helpers.FontHelper") && m.getMethodName().equals("renderRotatedText")) {
                        AddFlipVariables.AddFields(m.getEnclosingClass());
                        m.replace("{" +
                                "$6 *= flipScale;" +
                                "$_ = $proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }

    @SpirePatch(
            cls="com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder",
            method="render"
    )
    public static class CardGlowBorderRender
    {
        public static ExprEditor Instrument()
        {
            return new ExprEditor()
            {
                @Override
                public void edit(MethodCall m) throws CannotCompileException
                {
                    if (m.getClassName().equals("com.badlogic.gdx.graphics.g2d.SpriteBatch") && m.getMethodName().equals("draw")) {
                        try {
                            AddFlipVariables.AddFields(m.getEnclosingClass().getClassPool().getCtClass("com.megacrit.cardcrawl.cards.AbstractCard"));
                        } catch (NotFoundException e) {
                            e.printStackTrace();
                        }
                        m.replace("{" +
                                "$8 *= card.flipScale;" +
                                "$_ = $proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }
}
