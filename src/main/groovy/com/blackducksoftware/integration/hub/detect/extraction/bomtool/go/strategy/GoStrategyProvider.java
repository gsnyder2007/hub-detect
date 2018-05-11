package com.blackducksoftware.integration.hub.detect.extraction.bomtool.go.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blackducksoftware.integration.hub.detect.extraction.strategy.StrategyProvider;

@Component
public class GoStrategyProvider extends StrategyProvider {

    @Autowired
    public GoCliStrategy goFallbackStrategy;

    @Autowired
    public GoDepsStrategy goDepsStrategy;

    @Autowired
    public GoLockStrategy goLockStrategy;

    @Autowired
    public GoVndrStrategy goVndrStrategy;

    @Override
    public void init() {

        goFallbackStrategy.yieldsTo(goDepsStrategy);
        goFallbackStrategy.yieldsTo(goVndrStrategy);
        goFallbackStrategy.yieldsTo(goLockStrategy);

        add(goDepsStrategy, goVndrStrategy, goLockStrategy, goFallbackStrategy);

    }

}