package com.ns.chatbot.model;

import lombok.Builder;

/**
 * Represents a person with basic demographic information.
 * <p>
 * This record captures personal details including:
 * <ul>
 *   <li>Name (first and last)</li>
 *   <li>Age</li>
 *   <li>Gender</li>
 *   <li>Nationality</li>
 * </ul>
 *
 * @param id          Unique identifier for the person
 * @param firstName   The person's first name
 * @param lastName    The person's last name
 * @param age         The person's age in years
 * @param gender      The person's gender (from the Gender enum)
 * @param nationality The person's nationality or country of origin
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@Builder
public record Person(Integer id, String firstName, String lastName,
                     int age, Gender gender, String nationality) {

}

