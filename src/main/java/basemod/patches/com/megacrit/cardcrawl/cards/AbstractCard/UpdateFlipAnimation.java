package basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;

@SpirePatch(
        cls="com.megacrit.cardcrawl.cards.AbstractCard",
        method="update"
)
public class UpdateFlipAnimation
{
    public static void Postfix(AbstractCard __instance)
    {
        float flipScale = AddFlipVariables.getFlipScale(__instance);
        float targetFlipScale = AddFlipVariables.getTargetFlipScale(__instance);

        AddFlipVariables.setFlipScale(__instance, MathHelper.cardScaleLerpSnap(flipScale, targetFlipScale));
        if (Settings.FAST_MODE) {
            AddFlipVariables.setFlipScale(__instance, MathHelper.cardScaleLerpSnap(flipScale, targetFlipScale));
        }

        if (AddFlipVariables.getFlipScale(__instance) <= 0.1f) {
            __instance.isFlipped = !__instance.isFlipped;
            AddFlipVariables.setTargetFlipScale(__instance, 1.0f);
        }
    }
}
