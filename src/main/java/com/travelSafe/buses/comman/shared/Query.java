package com.travelSafe.buses.comman.shared;

public interface Query<I, O> {

  O execute(I input);
}