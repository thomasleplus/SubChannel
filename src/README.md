# Source

Maven sources for SubChannel, a demo of a cryptographic subliminal channel.

- `main/java/org/leplus/channel/` — the app:
  - `Main.java` — entry point.
  - `KeyGen.java`, `Sign.java`, `Verify.java` — key generation and
    sign/verify operations that carry the hidden channel.
  - `BiasedPRNGenerator.java`, `FileListener.java` — supporting utilities.

Build with `./mvnw`.
