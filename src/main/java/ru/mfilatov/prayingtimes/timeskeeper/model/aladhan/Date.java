package ru.mfilatov.prayingtimes.timeskeeper.model.aladhan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Date(String readable) {}
