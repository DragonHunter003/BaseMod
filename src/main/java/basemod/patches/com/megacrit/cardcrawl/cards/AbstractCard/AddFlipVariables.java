package basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.*;

import java.lang.reflect.Field;

public class AddFlipVariables
{
    static void AddFields(CtClass ctClass)
    {
        try {
            ctClass.getDeclaredField("flipScale");
        } catch (NotFoundException e) {
            try {
                CtField f = CtField.make("public float flipScale = 1.0f;", ctClass);
                ctClass.addField(f);
            } catch (CannotCompileException e1) {
                e1.printStackTrace();
            }
        }
        try {
            ctClass.getDeclaredField("targetFlipScale");
        } catch (NotFoundException e) {
            try {
                CtField f = CtField.make("public float targetFlipScale = 1.0f;", ctClass);
                ctClass.addField(f);
            } catch (CannotCompileException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static float getFlipScale(AbstractCard card)
    {
        try {
            Field f = AbstractCard.class.getDeclaredField("flipScale");
            return (float)f.get(card);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return 1.0f;
    }

    public static float getTargetFlipScale(AbstractCard card)
    {
        try {
            Field f = AbstractCard.class.getDeclaredField("targetFlipScale");
            return (float)f.get(card);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return 1.0f;
    }

    public static void setFlipScale(AbstractCard card, float v)
    {
        try {
            Field f = AbstractCard.class.getDeclaredField("flipScale");
            f.set(card, v);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void setTargetFlipScale(AbstractCard card, float v)
    {
        try {
            Field f = AbstractCard.class.getDeclaredField("targetFlipScale");
            f.set(card, v);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void startFlip(AbstractCard card)
    {
        setTargetFlipScale(card, 0.0f);
    }
}
