package com.wetech.demo.web3j.controller;

import com.wetech.demo.web3j.service.LCTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/lc")
@RequiredArgsConstructor
public class LCTokenController {

    private final LCTokenService tokenService;

    /**
     * Deploy a new LCToken contract
     * @return the address of the deployed contract
     */
    @PostMapping("/deploy")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deployContract() {
        return tokenService.deployContract()
                .thenApply(address -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("contractAddress", address);
                    response.put("message", "LCToken contract deployed successfully");
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Load an existing contract
     * @param address the address of the contract to load
     * @return a success message
     */
    @PostMapping("/load")
    public ResponseEntity<Map<String, String>> loadContract(@RequestParam String address) {
        tokenService.loadContract(address);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Contract loaded successfully");
        response.put("contractAddress", address);
        return ResponseEntity.ok(response);
    }

    /**
     * Mint new tokens
     * @param toAddress the recipient address
     * @param amount the amount to mint
     * @return the transaction receipt details
     */
    @PostMapping("/mint")
    public CompletableFuture<ResponseEntity<Map<String, String>>> mint(
            @RequestParam String toAddress,
            @RequestParam String amount) {

        BigInteger mintAmount = new BigInteger(amount);
        return tokenService.mint(toAddress, mintAmount)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("gasUsed", receipt.getGasUsed().toString());
                    response.put("status", receipt.getStatus());
                    response.put("message", "Tokens minted successfully");
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Transfer tokens
     * @param toAddress the recipient address
     * @param amount the amount to transfer
     * @return the transaction receipt details
     */
    @PostMapping("/transfer")
    public CompletableFuture<ResponseEntity<Map<String, String>>> transfer(
            @RequestParam String toAddress,
            @RequestParam String amount) {

        BigInteger transferAmount = new BigInteger(amount);
        return tokenService.transfer(toAddress, transferAmount)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("gasUsed", receipt.getGasUsed().toString());
                    response.put("status", receipt.getStatus());
                    response.put("message", "Transfer completed successfully");
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get balance of an address
     * @param address the address to check
     * @return the balance amount
     */
    @GetMapping("/balance")
    public CompletableFuture<ResponseEntity<Map<String, String>>> balanceOf(@RequestParam String address) {
        return tokenService.balanceOf(address)
                .thenApply(balance -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("address", address);
                    response.put("balance", balance.toString());
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Approve a spender
     * @param spenderAddress the spender address
     * @param amount the amount to approve
     * @return the transaction receipt details
     */
    @PostMapping("/approve")
    public CompletableFuture<ResponseEntity<Map<String, String>>> approve(
            @RequestParam String spenderAddress,
            @RequestParam String amount) {

        BigInteger approveAmount = new BigInteger(amount);
        return tokenService.approve(spenderAddress, approveAmount)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("gasUsed", receipt.getGasUsed().toString());
                    response.put("status", receipt.getStatus());
                    response.put("message", "Approval completed successfully");
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * 获取后端服务地址（部署者地址）
     */
    @GetMapping("/service-address")
    public ResponseEntity<Map<String, String>> getServiceAddress() {
        try {
            // 需要在LCTokenService中添加getServiceAddress方法
            String serviceAddress = tokenService.getServiceAddress();
            Map<String, String> response = new HashMap<>();
            response.put("serviceAddress", serviceAddress);
            response.put("message", "Service address retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to get service address: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    /**
     * Transfer from an approved address
     * @param fromAddress the address to transfer from
     * @param toAddress the address to transfer to
     * @param amount the amount to transfer
     * @return the transaction receipt details
     */
    @PostMapping("/transfer-from")
    public CompletableFuture<ResponseEntity<Map<String, String>>> transferFrom(
            @RequestParam String fromAddress,
            @RequestParam String toAddress,
            @RequestParam String amount) {

        BigInteger transferAmount = new BigInteger(amount);
        return tokenService.transferFrom(fromAddress, toAddress, transferAmount)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("gasUsed", receipt.getGasUsed().toString());
                    response.put("status", receipt.getStatus());
                    response.put("message", "Transfer from completed successfully");
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get allowance
     * @param ownerAddress the owner address
     * @param spenderAddress the spender address
     * @return the allowance amount
     */
    @GetMapping("/allowance")
    public CompletableFuture<ResponseEntity<Map<String, String>>> allowance(
            @RequestParam String ownerAddress,
            @RequestParam String spenderAddress) {

        return tokenService.allowance(ownerAddress, spenderAddress)
                .thenApply(allowance -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("owner", ownerAddress);
                    response.put("spender", spenderAddress);
                    response.put("allowance", allowance.toString());
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get token name
     * @return the token name
     */
    @GetMapping("/name")
    public CompletableFuture<ResponseEntity<Map<String, String>>> getName() {
        return tokenService.name()
                .thenApply(name -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("name", name);
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get token symbol
     * @return the token symbol
     */
    @GetMapping("/symbol")
    public CompletableFuture<ResponseEntity<Map<String, String>>> getSymbol() {
        return tokenService.symbol()
                .thenApply(symbol -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("symbol", symbol);
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get token decimals
     * @return the token decimals
     */
    @GetMapping("/decimals")
    public CompletableFuture<ResponseEntity<Map<String, String>>> getDecimals() {
        return tokenService.decimals()
                .thenApply(decimals -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("decimals", decimals.toString());
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get total supply
     * @return the total supply
     */
    @GetMapping("/total-supply")
    public CompletableFuture<ResponseEntity<Map<String, String>>> getTotalSupply() {
        return tokenService.totalSupply()
                .thenApply(supply -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("totalSupply", supply.toString());
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Burn tokens
     * @param amount the amount to burn
     * @return the transaction receipt details
     */
    @PostMapping("/burn")
    public CompletableFuture<ResponseEntity<Map<String, String>>> burn(@RequestParam String amount) {
        BigInteger burnAmount = new BigInteger(amount);
        return tokenService.burn(burnAmount)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("gasUsed", receipt.getGasUsed().toString());
                    response.put("status", receipt.getStatus());
                    response.put("message", "Tokens burned successfully");
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Burn tokens from a specific address
     * @param fromAddress the address to burn from
     * @param amount the amount to burn
     * @return the transaction receipt details
     */
    @PostMapping("/burn-from")
    public CompletableFuture<ResponseEntity<Map<String, String>>> burnFrom(
            @RequestParam String fromAddress,
            @RequestParam String amount) {

        BigInteger burnAmount = new BigInteger(amount);
        return tokenService.burnFrom(fromAddress, burnAmount)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("gasUsed", receipt.getGasUsed().toString());
                    response.put("status", receipt.getStatus());
                    response.put("message", "Tokens burned from address successfully");
                    response.put("contractAddress", tokenService.getContractAddress());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Get the address of the currently loaded contract
     * @return the contract address
     */
    @GetMapping("/address")
    public ResponseEntity<Map<String, String>> getContractAddress() {
        String address = tokenService.getContractAddress();
        Map<String, String> response = new HashMap<>();
        if (address != null) {
            response.put("contractAddress", address);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "No contract loaded");
            return ResponseEntity.ok(response);
        }
    }

    /**
     * Health check endpoint
     * @return health status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "LCToken service is running");
        response.put("contractLoaded", tokenService.getContractAddress() != null ? "true" : "false");
        return ResponseEntity.ok(response);
    }
}