package basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method="renderPortrait"
)
public class RenderPortraitXScale
{
    public static ExprEditor Instrument()
    {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException
            {
                if (m.getClassName().equals("com.badlogic.gdx.graphics.g2d.SpriteBatch") && m.getMethodName().equals("draw")) {
                    AddFlipVariables.AddFields(m.getEnclosingClass());
                    m.replace("{" +
                            "$8 *= this.flipScale;" +
                            "$_ = $proceed($$);" +
                            "}");
                }
            }
        };
    }
}
