package com.travelSafe.buses;

public interface Command<I, O> {

  O execute(I input);
}