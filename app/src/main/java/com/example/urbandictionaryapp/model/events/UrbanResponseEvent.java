package com.example.urbandictionaryapp.model.events;

import com.example.urbandictionaryapp.model.UrbanResponse;

public class UrbanResponseEvent {

    private UrbanResponse urbanResponse;

    public UrbanResponseEvent(UrbanResponse urbanResponse) { this.urbanResponse = urbanResponse; }

    public UrbanResponse getUrbanResponse() { return urbanResponse; }

}
