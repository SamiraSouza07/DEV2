package com.example.apibancopicpay.service;

import com.example.apibancopicpay.models.Cliente;
import com.example.apibancopicpay.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
    public Cliente buscarClientePorId(String cpf){
        return clienteRepository.findById(cpf).orElseThrow(()->
                new RuntimeException("Cliente não encontrado"));
    }

    public void salvarCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }
    public Cliente excluirCliente(String cpf){
        Optional<Cliente> cliente = clienteRepository.findById(cpf);
        if(cliente.isPresent()){
            clienteRepository.deleteById(cpf);
            return cliente.get();
        }
        return null;
    }
    public List<Cliente> buscarClientesPorNome(String nome){
        return clienteRepository.findByNomeLikeIgnoreCaseOrderByNome(nome);
    }
    public List<Cliente> buscarClientesPorCpf(String cpf){
        return clienteRepository.findByCpfLikeOrderByNome(cpf);
    }
    public List<Cliente> buscarClientesPorTelefone(String telefone){
        return clienteRepository.findByTelefoneLikeOrderByNome(telefone);
    }
    public List<Cliente> buscarClientesPorEmail(String email){
        return clienteRepository.findByEmailLikeIgnoreCaseOrderByNome(email);
    }
    public List<Cliente> buscarClientesPorNomeEEmail(String nome, String email){
        return clienteRepository.findByNomeLikeIgnoreCaseAndEmailLikeIgnoreCaseOrderByNome(nome,email);
    }
    public List<Cliente> buscarClientesPorNomeETelefone(String nome, String telefone){
        return clienteRepository.findByNomeLikeIgnoreCaseAndTelefoneLikeOrderByNome(nome, telefone);
    }
    public List<Cliente> buscarClientesPorNomeECpf(String nome, String cpf){
        return clienteRepository.findByNomeLikeIgnoreCaseAndCpfLikeOrderByNome(nome,cpf);
    }

}
