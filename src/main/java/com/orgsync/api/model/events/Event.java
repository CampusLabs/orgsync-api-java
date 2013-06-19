package com.orgsync.api.model.events;

import java.util.List;

import com.orgsync.api.model.IdAndName;

public class Event {

    private int id;
    private String name;
    private String description;
    private boolean isPublic;
    private boolean isApproved;
    private IdAndName category;
    private IdAndName umbrellaCategory;
    private String location;
    private String htmlDescription;
    private int rsvps;
    private int orgId;
    private List<EventOccurrence> occurrences;
}
