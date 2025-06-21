package com.travel.safe.buses.comman.shared;

public interface Command<I, O> {

  O execute(I input);
}