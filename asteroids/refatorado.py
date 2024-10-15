# INTEGRANTES DO GRUPO
# Leila Akina Ino               10402951
# Livia Alabarse dos Santos     10403046
# Pedro Henrique Araujo Farias  10265432

import pygame
import random as rd
import math

# Abstração (espécie de interface) do desenho de um objeto, seja ele nave, bala ou asteroide
class Draw:
    def draw(object, screen):
        object.draw(screen)

# Abstração (espécie de interface) do movimento de um objeto, seja ele nave, bala ou asteroide
class Movement:
    def move(object, screen):
        object.move(screen)

# Classe responsável por verificar colisões entre jogador e asteroide e entre asteroide e bala
class Collision:
    # Implementação de colisão entre jogador e asteroide
    def check_player_collision(score, player, asteroids):
        for asteroid in asteroids:
            distance = math.hypot(player.x - asteroid.x, player.y - asteroid.y)
            if distance < asteroid.size:
                score = 0 # Se player foi acertado, reseta o score e reseta player
                player.reset()
        return score

    def check_bullet_collision(score, bullets, asteroids):
        bullets_to_remove = []
        asteroids_to_remove = []

        for bullet in bullets:
            for asteroid in asteroids:
                distance = math.hypot(bullet.x - asteroid.x, bullet.y - asteroid.y)
                if distance < asteroid.size:  
                    asteroids_to_remove.append(asteroid)
                    bullets_to_remove.append(bullet)
                    score += 10 # Se asteroide for atingido, incrementa score e
                    # remove tanto asteroide quanto bala
            
        for bullet in bullets_to_remove:
            if bullet in bullets:
                bullets.remove(bullet)
    
        for asteroid in asteroids_to_remove:
            if asteroid in asteroids:
                asteroids.remove(asteroid)

        return score

# Classe mãe das classes Player, Bullet e Asteroid
class Object:
    def __init__(self, x, y, angle):
        self.x = x
        self.y = y
        self.angle = angle

# Classe Player herda de classe Objeto
class Player(Object):
    def __init__(self):
        super().__init__(400, 300, 0)
        self.speed = 0
    
    def reset(self): # Reseta player para posição inicial
        super().__init__(400, 300, 0)
        self.speed = 0

    def shoot(self): # Método responsável por implementar a função de atirar
        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE]: # Instancia um novo objeto Bullet
            return Bullet(self.x, self.y, self.angle)
        else:
            return None

    # Definição do método draw de Player, o qual é abstraído pela class Draw
    def draw(self, screen):
        points = [
        (self.x + 15 * math.cos(math.radians(self.angle)), self.y - 15 * math.sin(math.radians(self.angle))),
        (self.x + 15 * math.cos(math.radians(self.angle + 120)), self.y - 15 * math.sin(math.radians(self.angle + 120))),
        (self.x + 15 * math.cos(math.radians(self.angle + 240)), self.y - 15 * math.sin(math.radians(self.angle + 240)))
        ]
            
        pygame.draw.polygon(screen, (255, 255, 255), points)
        
    # Definição do método move de Player, o qual é abstraído pela classe Movement
    def move(self, screen):
        keys = pygame.key.get_pressed()
    
        if keys[pygame.K_LEFT]:
            self.angle += 5  
        if keys[pygame.K_RIGHT]:
            self.angle -= 5  
        if keys[pygame.K_UP]:
            self.speed = 5  
        elif keys[pygame.K_DOWN]:
            self.speed = -3 
        else:
            self.speed = 0  

        self.x += self.speed * math.cos(math.radians(self.angle))
        self.y -= self.speed * math.sin(math.radians(self.angle))

        if self.x < 0: self.x = 800
        if self.x > 800: self.x = 0
        if self.y < 0: self.y = 600
        if self.y > 600: self.y = 0

# Classe Bullet herda de classe Objeto
class Bullet(Object):
    def __init__(self, x, y, angle):
        super().__init__(x, y, angle) 
    
    def draw(self, screen): # Definição do método draw em Bullet, abstraído por Draw
        pygame.draw.circle(screen, (255, 255, 255), (int(self.x), int(self.y)), 3)

    def move(self, screen): # Definição do método move em Bullet, abstraído por Movement
        self.x += 10 * math.cos(math.radians(self.angle))
        self.y -= 10 * math.sin(math.radians(self.angle))

# Classe Bullet herda de classe Objeto        
class Asteroid(Object):
    def __init__(self):
        super().__init__(rd.randint(0, 800), rd.randint(0, 600), rd.randint(0, 360))
        self.size = rd.randint(20, 50)
        self.speed = rd.random() * 2 + 1

    def draw(self, screen): # Definição de draw em Asteroid, abstraído por Draw
        pygame.draw.circle(screen, (255, 255, 255), (int(self.x), int(self.y)), self.size)

    def move(self, screen): # Definição de move em Asteroid, abstraído por Movement
        self.x += self.speed * math.cos(math.radians(self.angle))
        self.y -= self.speed * math.sin(math.radians(self.angle))
        if self.x < 0: self.x = 800
        if self.x > 800: self.x = 0     
        if self.y < 0: self.y = 600
        if self.y > 600: self.y = 0

# Classe responsável pelo modelo e execução do jogo
class MainGame:
    def __init__(self):
        pygame.init()
        self.player = Player() # Instancia objeto da classe Player
        self.screen = pygame.display.set_mode((800, 600))
        pygame.display.set_caption("Asteroids")
        self.score = 0
        self.font = pygame.font.SysFont(None, 36)
        self.bullets = []
        self.asteroids = []
    
    def create_asteroids(self):
        if len(self.asteroids) < 5:
            for _ in range(5 - len(self.asteroids)):
                # Adiciona um novo objeto da classe Asteroid ao vetor
                self.asteroids.append(Asteroid())

    def move_objects(self, objects):
        for obj in objects:
            Movement.move(obj, self.screen)
            # Utiliza Movement para invocar método move correspondente à implementação nas classes

    def draw_objects(self, objects):
        for obj in objects:
            Draw.draw(obj, self.screen)
            # Utiliza Draw para invocar método draw correspondente à implementação nas classes

    def game_loop(self):
        running = True
        while running:
            self.screen.fill((0, 0, 0))
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False

            Movement.move(self.player, self.screen)
            self.move_objects(self.asteroids)
            self.move_objects(self.bullets)
            # Verifica se bala está nos limites da tela
            self.bullets = [bullet for bullet in self.bullets if 0 < bullet.x < 800 and 0 < bullet.y < 600]

            # As colisões atualizam o score do jogador
            self.score = Collision.check_bullet_collision(self.score, self.bullets, self.asteroids)
            self.score = Collision.check_player_collision(self.score, self.player, self.asteroids)
           
            self.create_asteroids()
            
            newBullet = self.player.shoot()
            if newBullet != None:
                self.bullets.append(newBullet)
                
            # Draw abstrai os métodos correspondentes para cada classe
            Draw.draw(self.player, self.screen)
            self.draw_objects(self.bullets)
            self.draw_objects(self.asteroids)

            score_text = self.font.render(f'Score: {self.score}', True, (255, 255, 255))
            self.screen.blit(score_text, (10, 10))

            pygame.display.flip()
            pygame.time.delay(30)

        pygame.quit()

class Main:
    def main():
        jogo = MainGame()
        jogo.game_loop()

Main.main()
