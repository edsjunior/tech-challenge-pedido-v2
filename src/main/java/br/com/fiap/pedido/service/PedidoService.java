package br.com.fiap.pedido.service;

import br.com.fiap.pedido.dto.ClienteDTO;
import br.com.fiap.pedido.dto.PagamentoDTO;
import br.com.fiap.pedido.dto.ProdutoDTO;
import br.com.fiap.pedido.model.Pedido;
import br.com.fiap.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    private WebClient.Builder webClientBuilder;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(UUID id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(UUID id, Pedido pedidoDetails) {
        Pedido pedido = getPedidoById(id);
        if (pedido != null) {
            // pedido.setDescricao(pedidoDetails.getDescricao());
            // pedido.setValor(pedidoDetails.getValor());
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public void deletePedido(UUID id) {
        pedidoRepository.deleteById(id);
    }

    public ClienteDTO getClienteById(UUID clienteId) {
        String url = "http://cliente-service/clientes/" + clienteId;
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block(); // Use block para obter resultado síncrono
    }

    // Método para buscar lista de produtos
    public List<ProdutoDTO> getProdutosByIds(List<UUID> produtoIds) {
        return produtoIds.stream()
                .map(this::getProdutoById)
                .toList();
    }

    // Adicione métodos para buscar dados de pagamento se necessário
    public PagamentoDTO getPagamentoById(UUID pagamentoId) {
        String url = "http://pagamento-service/pagamentos/" + pagamentoId;
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(PagamentoDTO.class)
                .block();
    }

    public ProdutoDTO getProdutoById(UUID produtoId) {
        String url = "http://produto-service/produto/" + produtoId;
        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ProdutoDTO.class)
                .block();
    }
}
