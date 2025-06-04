package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserSimpleDto(@Nullable Long Id, String firstName, String lastName) {

}
