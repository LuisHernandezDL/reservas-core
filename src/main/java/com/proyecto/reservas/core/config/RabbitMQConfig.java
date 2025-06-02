/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.reservas.core.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author Luis
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange tareasExchange() {
        return new TopicExchange("tareas-exchange");
    }

    @Bean
    public Queue tareasQueue() {
        return new Queue("tareas-queue");
    }

    @Bean
    public Binding binding(Queue tareasQueue, TopicExchange tareasExchange) {
        return BindingBuilder.bind(tareasQueue).to(tareasExchange).with("tareas.evento");
    }
    
    @Bean
        public TopicExchange accionesExchange() {
         return new TopicExchange("acciones-exchange");
        }

        @Bean
    public Queue accionesQueue() {
        return new Queue("acciones-queue");
    }

    @Bean
    public Binding accionesBinding(Queue accionesQueue, TopicExchange accionesExchange) {
        return BindingBuilder.bind(accionesQueue).to(accionesExchange).with("acciones.evento");
    }
        @Bean
    public TopicExchange colaExchange() {
        return new TopicExchange("cola-exchange");
    }

    @Bean
    public Queue colaQueue() {
        return new Queue("cola-queue");
    }

    @Bean
    public Binding colaBinding(Queue colaQueue, TopicExchange colaExchange) {
        return BindingBuilder.bind(colaQueue).to(colaExchange).with("cola.evento");
    }
    
        @Bean
    public TopicExchange arbolExchange() {
        return new TopicExchange("arbol-exchange");
    }

    @Bean
    public Queue arbolQueue() {
        return new Queue("arbol-queue");
    }

    @Bean
    public Binding arbolBinding(Queue arbolQueue, TopicExchange arbolExchange) {
        return BindingBuilder.bind(arbolQueue).to(arbolExchange).with("arbol.evento");
    }

 }
