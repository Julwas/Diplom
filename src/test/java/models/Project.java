package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.testng.ITestResult;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @EqualsAndHashCode.Exclude
    @SerializedName(value = "Id")
    private int projectId;

    private String name;
    private String description;

    @SerializedName(value = "symbol_id")
    @JsonProperty("symbol_id")
    private int symbolId;
}
