package eu.greyson.ongarbayev.sultan.paymenttracker.service;

import java.util.concurrent.ExecutorService;

/**
 * Base class for all services.
 * Actually it's wrapper around {@link Runnable} interface, which has
 * empty {@code run} method implementation and has ExecutorService objects
 * for input and output executors. In some cases it's necessary to have
 * access to them.
 */
public abstract class BaseService implements Runnable {

    protected ExecutorService inputExecutorService;
    protected ExecutorService outputExecutorService;

    public BaseService(
            ExecutorService inputExecutorService,
            ExecutorService outputExecutorService
    ) {
        this.inputExecutorService = inputExecutorService;
        this.outputExecutorService = outputExecutorService;
    }

    @Override
    public abstract void run();
}
