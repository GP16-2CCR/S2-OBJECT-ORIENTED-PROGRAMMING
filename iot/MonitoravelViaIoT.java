package iot;

import modelo.DadosSensor;

/**
 * Contrato de comportamento para trechos equipados com sensores IoT.
 * Desacoplado de hierarquia de classes — qualquer trecho pode implementar.
 */
public interface MonitoravelViaIoT {

    DadosSensor transmitirDadosSensor();
}
