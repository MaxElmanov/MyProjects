package main.event;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class MathRegister extends BlockJUnit4ClassRunner
{
    MathListener mathListener;

    public MathRegister(Class<MathListener> clazz) throws InitializationError
    {
        super(clazz);
        mathListener = new MathListener();
    }

    public void run(RunNotifier notifier){
        notifier.addListener(mathListener);
        super.run(notifier);
    }
}
