package com.travelSafe.buses.comman.shared;

public interface Command<I, O> {

  O execute(I input);
}