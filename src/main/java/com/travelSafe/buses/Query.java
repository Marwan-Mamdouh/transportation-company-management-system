package com.travelSafe.buses;

public interface Query<I, O> {

  O execute(I input);
}