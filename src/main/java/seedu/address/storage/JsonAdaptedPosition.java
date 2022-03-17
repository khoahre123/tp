package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;

public class JsonAdaptedPosition {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";

    private final String positionName;
    private final String description;
    private final String positionOpening;
    private final List<JsonAdaptedRequirement> requirements = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given interview details.
     */
    @JsonCreator
    public JsonAdaptedPosition(@JsonProperty("positionName") String positionName,
            @JsonProperty("description") String description, @JsonProperty("positionOpening") String positionOpening,
            @JsonProperty("requirements") List<JsonAdaptedRequirement> requirements) {
        this.positionName = positionName;
        this.description = description;
        this.positionOpening = positionOpening;

        if (requirements != null) {
            this.requirements.addAll(requirements);
        }
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedPosition(Position source) {
        positionName = source.getPositionName().positionName;
        description = source.getDescription().descriptionText;
        positionOpening = source.getPositionOpenings().toString();

        requirements.addAll(source.getRequirements().stream()
                .map(JsonAdaptedRequirement::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted applicant object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted applicant.
     */
    public Position toModelType() throws IllegalValueException {
        final List<Requirement> positionRequirements = new ArrayList<>();
        for (JsonAdaptedRequirement requirement: requirements) {
            positionRequirements.add(requirement.toModelType());
        }
        final PositionName modelPositionName = new PositionName(positionName);

        final Description modelDescription = new Description(description);

        final PositionOpenings modelPositionOpenings = new PositionOpenings(positionOpening);

        final Set<Requirement> modelRequirement = new HashSet<>(positionRequirements);

        return new Position(modelPositionName, modelDescription, modelPositionOpenings, modelRequirement);
    }
}
