package com.travel.safe.buses.comman.shared;

public interface Query<I, O> {

  O execute(I input);
}