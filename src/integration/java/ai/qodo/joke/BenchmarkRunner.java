package ai.qodo.joke;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BenchmarkRunner {

    public static final String WORDS = """
            Creating a meaningful 6,000+-word text is a substantial task and depends on the purpose or theme. Here’s a random narrative-style piece that explores a mix of genres, complete with a creative storyline and characters. If you have a specific theme or idea in mind, let me know, and I can tailor the content accordingly!
            
            The Forgotten City
            
            Chapter 1: The Mysterious Map (Word count: ~1,000)
            
            It started with an envelope that appeared on Eleanor’s doorstep. The parchment inside was old, with frayed edges and the faint smell of the sea. She unfolded it carefully, revealing a map that seemed older than time itself. The intricate details of a forgotten city were etched into the paper, marked by symbols and codes she couldn’t decipher. It was unlike anything she had seen, and for a historian like her, it was irresistible.
            
            Eleanor had spent her career piecing together the stories of ancient civilizations. She had scoured libraries, traversed deserts, and even braved deep caves in search of artifacts. But this map, with its cryptic allure, was unlike anything in her vast collection of discoveries. She spent hours staring at it under a magnifying glass, her mind buzzing with theories.
            
            “Who could have sent this?” she muttered to herself.
            
            A knock at her door broke her concentration. It was Oliver, her longtime friend and fellow adventurer. He was carrying his usual gear—maps, notebooks, and a thermos of coffee.
            
            “I thought you might need a second opinion,” he said with a grin, holding up his equipment.
            
            Eleanor wasted no time showing him the map. Oliver’s eyes widened as he traced the intricate patterns with his finger.
            
            “This… this might be it,” he said, almost whispering. “The Lost City of Solace. If we follow this map, we could find it.”
            
            Chapter 2: Journey Through the Jungle (Word count: ~1,500)
            
            Eleanor and Oliver began their journey a week later. The map led them to a dense jungle in South America. The terrain was unforgiving, filled with tangled vines, hidden streams, and the constant hum of insects. Every step felt like a battle against nature itself.
            
            Their guide, a local named Santiago, warned them of the dangers ahead. “Many have tried to find the city,” he said. “None have returned.”
            
            Eleanor’s determination only grew with each obstacle. She and Oliver spent nights studying the map by the flickering light of their campfire. The symbols began to make sense, revealing a path hidden beneath the canopy of trees.
            
            Days turned into weeks as they hacked their way through the jungle. Along the way, they encountered ruins that hinted at a once-thriving civilization. Stone carvings told stories of gods and heroes, battles and peace. Eleanor meticulously documented everything, her excitement growing with each discovery.
            
            Finally, they reached a clearing where the jungle gave way to an immense stone gate covered in moss and vines. It was the entrance to the city.
            
            Chapter 3: The Forgotten City (Word count: ~2,000)
            
            The city of Solace was breathtaking. Towering stone structures rose from the ground, their architecture unlike anything Eleanor had studied before. The streets were paved with intricately designed tiles, and statues of mythical creatures stood guard at every corner.
            
            Eleanor and Oliver explored cautiously, their senses heightened. The air was thick with an unexplainable energy, as if the city itself were alive. They found evidence of advanced technology—water systems, astronomical devices, and carvings that seemed to depict modern machines.
            
            As they ventured deeper, they discovered a grand temple at the heart of the city. Its walls were adorned with murals that told the story of Solace’s rise and fall. The city had been a utopia, ruled by wise leaders who valued knowledge and harmony. But greed and power had led to its destruction. The final mural depicted a cataclysmic event, with fire raining from the sky and the city sinking into darkness.
            
            “This place is a treasure trove,” Eleanor said, her voice tinged with awe. “It’s like the stories of Atlantis, but real.”
            
            But their excitement was short-lived. As they exited the temple, they heard footsteps. A group of armed men emerged from the shadows, their leader holding a gun.
            
            “Thank you for leading us here,” he said with a smirk. “We’ll take it from here.”
            
            Chapter 4: A Race Against Time (Word count: ~1,500)
            
            asshole <- here is the swearword
            
            Eleanor and Oliver were taken hostage by the mercenaries. The leader, a man named Vargas, revealed that he had been searching for Solace for years. He planned to loot its treasures and sell them to the highest bidder.
            
            But Eleanor was not one to give up easily. She and Oliver hatched a plan to escape. Using their knowledge of the city’s layout, they navigated through secret tunnels and hidden passages. Along the way, they activated traps that had been designed to protect the city from intruders. The mercenaries were caught off guard, and chaos ensued.
            
            In the midst of their escape, Eleanor stumbled upon a chamber filled with ancient artifacts. Among them was a glowing orb that pulsed with energy. She felt an inexplicable connection to it, as if it were calling to her. Without thinking, she took it.
            
            The orb seemed to react to her touch, emitting a bright light that filled the room. The walls trembled, and a low rumble echoed through the city.
            
            “We need to get out of here!” Oliver shouted.
            
            As they ran, the city began to collapse around them. Stone structures crumbled, and the ground split open, revealing deep chasms. Eleanor and Oliver barely made it out alive, the orb still in Eleanor’s hands.
            
            Chapter 5: The Aftermath (Word count: ~1,000)
            
            Back home, Eleanor and Oliver struggled to make sense of what had happened. The orb seemed to hold immense power, but its purpose remained a mystery. Eleanor knew she couldn’t keep it hidden forever.
            
            “This could change everything,” she said. “But it’s too dangerous to fall into the wrong hands.”
            
            They decided to entrust the orb to a team of researchers who could study it under strict security. As for the city of Solace, it had returned to the jungle, its secrets buried once more.
            
            Eleanor and Oliver’s discovery made headlines around the world. They became renowned for their work, but they knew the true value of their journey wasn’t the fame or recognition. It was the knowledge they had gained and the bond they had forged.
            
            As Eleanor stared at the map one last time, she felt a sense of closure. The city of Solace might be gone, but its legacy would live on through their stories.
            
            """;
    Scrubber iScrubber = new IScrubber();
    Scrubber dScrubber = new DScrubber();

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(BenchmarkRunner.class.getSimpleName())
                .result("build/benchmark-results.scsv") // Output file
                .resultFormat(ResultFormatType.SCSV) // Use SCSV format
                .forks(1).build();

        Collection<RunResult> results = new Runner(opt).run();

        // Analyze results and calculate performance difference
        double declarativeScore = 0;
        double imperativeScore = 0;

        for (RunResult result : results) {
            String benchmarkName = result.getParams().getBenchmark();
            double score = result.getPrimaryResult().getScore();

            if (benchmarkName.endsWith("declarative")) {
                declarativeScore = score;
            } else if (benchmarkName.endsWith("imperative")) {
                imperativeScore = score;
            }
        }

        if (declarativeScore > 0 && imperativeScore > 0) {
            if (declarativeScore < imperativeScore) {
                double slowerPercentage = ((imperativeScore - declarativeScore) / declarativeScore) * 100;
                System.out.printf("DeclarativeScrubber is %.2f%% faster than ImperativeScrubber.%n", slowerPercentage);
            } else if (declarativeScore > imperativeScore) {
                double slowerPercentage = ((declarativeScore - imperativeScore) / imperativeScore) * 100;
                System.out.printf("ImperativeScrubber is %.2f%% faster than DeclarativeScrubber.%n", slowerPercentage);
            } else {
                System.out.println("Both scrubbers have the same performance.");
            }
        } else {
            System.out.println("Benchmark results could not be calculated.");
        }


    }


    @Benchmark
    @Warmup(iterations = 30, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 30, time = 1, timeUnit = TimeUnit.SECONDS)
    public void imperative() {
        iScrubber.scrub(BenchmarkRunner.WORDS);
    }

    @Benchmark
    @Warmup(iterations = 30, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 30, time = 1, timeUnit = TimeUnit.SECONDS)
    public void declarative() {
        dScrubber.scrub(BenchmarkRunner.WORDS);
    }


}
